package com.bezzo.moviecatalogue.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData


abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    lateinit var mExecutor: AppExecutor

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    constructor(executors: AppExecutor){
        this.mExecutor = executors
        result.value = Resource(Status.LOADING, null, null)

        val dbSource: LiveData<ResultType> = loadFromDB()
        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if(shouldFetch(it)){
                fetchFromNetwork(dbSource)
            }
            else {
                result.addSource(dbSource) {
                    result.value = Resource(Status.SUCCESS, it, null)
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>){
        val apiResponse: LiveData<ApiResponse<RequestType>> = createCall()
        result.addSource(dbSource) {
            result.value = Resource(Status.LOADING, it, null)
        }
        result.addSource(apiResponse) {
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when(it.status){
                StatusResponse.SUCCESS -> {
                    mExecutor.diskIO.execute {
                        saveCallResult(it.body)
                        mExecutor.mainThread.execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource(Status.SUCCESS, newData, null)
                            }
                        }
                    }
                }
                StatusResponse.EMPTY -> {
                    mExecutor.mainThread.execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = Resource(Status.SUCCESS, newData, null)
                        }
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource(Status.ERROR, newData, it.message)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }
}