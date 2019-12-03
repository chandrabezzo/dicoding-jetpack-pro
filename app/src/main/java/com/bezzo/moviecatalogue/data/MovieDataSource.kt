package com.bezzo.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow
import com.bezzo.moviecatalogue.util.Resource

interface MovieDataSource {
    fun getMovies(): LiveData<MutableList<Movie>>

    fun getTvShows(): LiveData<MutableList<TvShow>>

    fun getFavoriteMovies(): LiveData<PagedList<Movie>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShow>>

    fun addMovie(movie: Movie)

    fun addTvShow(tvShow: TvShow)

    fun removeMovie(id: Int)

    fun removeTvShow(id: Int)
}