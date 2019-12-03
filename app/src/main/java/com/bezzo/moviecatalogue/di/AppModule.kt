package com.bezzo.moviecatalogue.di

import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import com.bezzo.moviecatalogue.adapter.FavoriteMovieRVAdapter
import com.bezzo.moviecatalogue.adapter.FavoriteTvShowRVAdapter
import com.bezzo.moviecatalogue.adapter.MovieRVAdapter
import com.bezzo.moviecatalogue.adapter.TvShowRVAdapter
import com.bezzo.moviecatalogue.data.repository.MovieRepository
import com.bezzo.moviecatalogue.data.RemoteRepository
import com.bezzo.moviecatalogue.data.local.LocalStorage
import com.bezzo.moviecatalogue.data.local.MovieDao
import com.bezzo.moviecatalogue.data.repository.LocalRepository
import com.bezzo.moviecatalogue.ui.about.AboutViewModel
import com.bezzo.moviecatalogue.util.AppExecutor
import com.bezzo.moviecatalogue.viewmodel.MovieViewModel
import com.bezzo.moviecatalogue.viewmodel.TvShowViewModel
import com.bezzo.moviecatalogue.util.JsonHelper
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { SessionHelper() }
    factory { CompositeDisposable() }
    single { Gson() }
    single { SchedulerProviderUtil() }
    single { LocalStorage.getInstance(androidContext())?.movieDao() }
    single { LocalRepository(LocalStorage.getInstance(androidContext())?.movieDao()) }
}

val repositoryModule = module {
    single {
        MovieRepository.getInstance(
            LocalRepository(get()),
            RemoteRepository.getInstance(JsonHelper(androidApplication())),
            AppExecutor()
        )
    }
}

val viewModelModule = module {
    viewModel { AboutViewModel(get()) }
    viewModel { MovieViewModel(get(), get()) }
    viewModel { TvShowViewModel(get(), get()) }
}
val rvAdapterModule = module {
    factory { MovieRVAdapter(androidContext(), ArrayList()) }
    factory { TvShowRVAdapter(androidContext(), ArrayList()) }
    factory { FavoriteMovieRVAdapter(androidContext(), ArrayList()) }
    factory { FavoriteTvShowRVAdapter(androidContext(), ArrayList()) }
}

val allModule = listOf(appModule, viewModelModule, rvAdapterModule, repositoryModule)