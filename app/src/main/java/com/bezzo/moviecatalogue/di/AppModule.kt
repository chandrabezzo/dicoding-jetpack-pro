package com.bezzo.moviecatalogue.di

import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import com.bezzo.moviecatalogue.adapter.MovieRVAdapter
import com.bezzo.moviecatalogue.features.about.AboutViewModel
import com.bezzo.moviecatalogue.features.movie.MovieViewModel
import com.bezzo.moviecatalogue.features.tvShow.TvShowViewModel
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { SessionHelper() }
    single { Gson() }
    single { SchedulerProviderUtil() }
}

val viewModelModule = module {
    viewModel { AboutViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
}
val rvAdapterModule = module {
    factory { MovieRVAdapter(androidContext(), ArrayList()) }
}

val allModule = listOf(appModule, viewModelModule, rvAdapterModule)