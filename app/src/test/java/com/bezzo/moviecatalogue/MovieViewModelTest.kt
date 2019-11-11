package com.bezzo.moviecatalogue

import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.features.movie.MovieViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieViewModelTest {

    var viewModel: MovieViewModel = MovieViewModel(SessionHelper())

    @Test
    fun calculate(){
        val list = viewModel.getMovie()
        assertEquals(list.size, 12)
    }
}