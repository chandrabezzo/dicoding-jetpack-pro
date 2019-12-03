package com.bezzo.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bezzo.core.base.BaseViewModel
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.repository.MovieRepository
import com.bezzo.moviecatalogue.data.model.TvShow
import com.bezzo.moviecatalogue.data.repository.LocalRepository
import com.bezzo.moviecatalogue.util.Resource

class TvShowViewModel(
    private val movieRepository: MovieRepository,
    sessionHelper: SessionHelper
) : BaseViewModel(sessionHelper) {

    fun getTv(): LiveData<MutableList<TvShow>> {
        return movieRepository.getTvShows()
    }

    fun getFavoriteTvShow(): LiveData<PagedList<TvShow>>{
        return movieRepository.getFavoriteTvShows()
    }

    fun addTvShow(tvShow: TvShow){
        movieRepository.addTvShow(tvShow)
    }

    fun removeTvShow(id: Int){
        movieRepository.removeTvShow(id)
    }
}