package com.bezzo.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bezzo.core.base.BaseViewModel
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.repository.LocalRepository
import com.bezzo.moviecatalogue.data.repository.MovieRepository
import com.bezzo.moviecatalogue.util.Resource

class MovieViewModel(
    private val movieRepository: MovieRepository,
    sessionHelper: SessionHelper
) : BaseViewModel(sessionHelper) {

    fun getMovie(): LiveData<MutableList<Movie>> {
        return movieRepository.getMovies()
    }

    fun getFavoriteMovies(): LiveData<PagedList<Movie>> {
        return movieRepository.getFavoriteMovies()
    }

    fun addMovie(movie: Movie){
        movieRepository.addMovie(movie)
    }

    fun removeMovie(id: Int){
        movieRepository.removeMovie(id)
    }
}