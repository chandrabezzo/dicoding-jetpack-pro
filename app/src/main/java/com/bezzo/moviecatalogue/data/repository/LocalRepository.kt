package com.bezzo.moviecatalogue.data.repository

import androidx.paging.DataSource
import com.bezzo.moviecatalogue.data.local.MovieDao
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow

class LocalRepository(private val movieDao: MovieDao) {

    fun getMovies(): DataSource.Factory<Int, Movie> {
        return movieDao.getMovies()
    }

    fun addMovie(movie: Movie) {
        movieDao.addMovie(movie)
    }

    fun insertsMovie(movies: ArrayList<Movie>) {
        movieDao.insertsMovie(movies)
    }

    fun deleteMovie(id: Int) {
        movieDao.deleteMovie(id)
    }

    fun getTvShows(): DataSource.Factory<Int, TvShow> {
        return movieDao.getTvShows()
    }

    fun addTvShow(tvShow: TvShow){
        movieDao.addTvShow(tvShow)
    }

    fun insertsTvShow(tvShows: ArrayList<TvShow>){
        movieDao.insertsTvShow(tvShows)
    }

    fun deleteTvShow(id: Int){
        movieDao.deleteTvShow(id)
    }
}