package com.bezzo.moviecatalogue.data.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow

@Dao
interface MovieDao {
    @WorkerThread
    @Query("SELECT * FROM ${AppConstant.DATA_MOVIE}")
    fun getMovies(): DataSource.Factory<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertsMovie(movies: ArrayList<Movie>): Array<Long>

    @Query("DELETE FROM ${AppConstant.DATA_MOVIE} WHERE id=:id")
    fun deleteMovie(id: Int): Int

    @WorkerThread
    @Query("SELECT * FROM ${AppConstant.DATA_TV}")
    fun getTvShows(): DataSource.Factory<Int, TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTvShow(tvShow: TvShow): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertsTvShow(tvShows: ArrayList<TvShow>): Array<Long>

    @Query("DELETE FROM ${AppConstant.DATA_TV} WHERE id=:id")
    fun deleteTvShow(id: Int): Int
}