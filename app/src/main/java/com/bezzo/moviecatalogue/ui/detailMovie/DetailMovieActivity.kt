package com.bezzo.moviecatalogue.ui.detailMovie

import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.extension.toast
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailMovieActivity : BaseActivity() {

    lateinit var data: Movie

    private val viewmodel: MovieViewModel by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        dataReceived?.getParcelable<Movie>(AppConstant.DATA_MOVIE)?.let {
            data = it
        }

        val image = "https://image.tmdb.org/t/p/w185/${data.posterPath}"
        GlideApp.with(this).load(image).into(iv_banner)
        tv_title.text = "${data.title} (${data.releaseDate})"
        tv_user_score.text = data.voteAverage.toString()
        tv_desc.text = data.overview
        tv_popularity.text = data.popularity.toString()

        ib_favorite.setOnClickListener {
            viewmodel.addMovie(data)
            toast(getString(R.string.berhasil_ditambahkan))
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail
    }
}
