package com.bezzo.moviecatalogue

import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.features.tvShow.TvShowViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class TvShowViewModelTest {

    var viewModel: TvShowViewModel = TvShowViewModel(SessionHelper())

    @Test
    fun calculate(){
        val list = viewModel.getTv()
        assertEquals(list.size, 12)
    }
}