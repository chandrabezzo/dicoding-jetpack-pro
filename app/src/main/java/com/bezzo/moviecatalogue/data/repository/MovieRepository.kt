package com.bezzo.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bezzo.moviecatalogue.data.LoadMovieCallback
import com.bezzo.moviecatalogue.data.LoadTvShowCallback
import com.bezzo.moviecatalogue.data.MovieDataSource
import com.bezzo.moviecatalogue.data.RemoteRepository
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow
import com.bezzo.moviecatalogue.util.AppExecutor

class MovieRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val appExecutor: AppExecutor):
    MovieDataSource {

    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getInstance(localRepository: LocalRepository, remoteData: RemoteRepository?,
                        appExecutor: AppExecutor): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        remoteData?.let {
                            INSTANCE =
                                MovieRepository(
                                    localRepository,
                                    remoteData,
                                    appExecutor
                                )
                        }
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getMovies(): LiveData<MutableList<Movie>> {
        val movieResult: MutableLiveData<MutableList<Movie>> = MutableLiveData()
        remoteRepository.getMovies(object: LoadMovieCallback {
            override fun onDataReceived(values: MutableList<Movie>) {
                val movies: MutableList<Movie> = ArrayList()
                for(counter: Int in 0 until values.size){
                    val response = values[counter]
                    val movie = Movie(response.popularity, response.voteCount, response.video,
                        response.posterPath, response.id, response.adult, response.backdropPath,
                        response.originalLanguage, response.originalTitle, response.title,
                        response.voteAverage, response.overview, response.releaseDate)
                    movies.add(movie)
                }
                movieResult.postValue(movies)
            }

            override fun onDataNotAvailable() {

            }
        })

        return movieResult
    }

    override fun getTvShows(): LiveData<MutableList<TvShow>> {
        val tvShowResult: MutableLiveData<MutableList<TvShow>> = MutableLiveData()
        remoteRepository.getTvShows(object: LoadTvShowCallback {
            override fun onDataReceived(values: MutableList<TvShow>) {
                val tvShows: MutableList<TvShow> = ArrayList()
                for(counter: Int in 0 until values.size){
                    val response = values[counter]
                    val tvShow = TvShow(response.originalName, response.name, response.popularity,
                        response.voteCount, response.firstAirDate, response.backdropPath, response.originalLanguage,
                        response.id, response.voteAverage, response.overview, response.posterPath)
                    tvShows.add(tvShow)
                }
                tvShowResult.postValue(tvShows)
            }

            override fun onDataNotAvailable() {

            }
        })

        return tvShowResult
    }

    override fun getFavoriteMovies(): LiveData<PagedList<Movie>> {
        return LivePagedListBuilder<Int, Movie>(localRepository.getMovies(), 5).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShow>> {
        return LivePagedListBuilder<Int, TvShow>(localRepository.getTvShows(), 5).build()
    }

    override fun addMovie(movie: Movie) {
        val runnable = Runnable { localRepository.addMovie(movie) }
        appExecutor.diskIO.execute(runnable)
    }

    override fun addTvShow(tvShow: TvShow) {
        val runnable = Runnable { localRepository.addTvShow(tvShow) }
        appExecutor.diskIO.execute(runnable)
    }

    override fun removeMovie(id: Int) {
        val runnable = Runnable { localRepository.deleteMovie(id) }
        appExecutor.diskIO.execute(runnable)
    }

    override fun removeTvShow(id: Int) {
        val runnable = Runnable { localRepository.deleteTvShow(id) }
        appExecutor.diskIO.execute(runnable)
    }
}