package com.bezzo.moviecatalogue.features.detailMovie

import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailMovieActivity : BaseActivity() {

    lateinit var data: Movie

    override fun onInitializedView(savedInstanceState: Bundle?) {
        dataReceived?.getParcelable<Movie>(AppConstant.DATA_MOVIE)?.let {
            data = it
        }

        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        displayHome()
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }

        GlideApp.with(this).load(data.image).into(iv_poster)
        tv_judul.text = "${data.title} (${data.releaseYear})"
        tv_user_score.text = data.userScore
        tv_deskripsi.text = data.description
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail
    }
}
