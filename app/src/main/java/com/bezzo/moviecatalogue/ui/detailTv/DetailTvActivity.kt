package com.bezzo.moviecatalogue.ui.detailTv

import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.extension.toast
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.TvShow
import com.bezzo.moviecatalogue.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.activity_detail_tv.*
import org.koin.android.ext.android.inject

class DetailTvActivity : BaseActivity() {

    lateinit var data: TvShow
    private val viewmodel: TvShowViewModel by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        dataReceived?.getParcelable<TvShow>(AppConstant.DATA_TV)?.let {
            data = it
        }

        val image = "https://image.tmdb.org/t/p/w185/${data.posterPath}"
        GlideApp.with(this).load(image).into(iv_banner)
        tv_title.text = "${data.name} (${data.firstAirDate})"
        tv_user_score.text = data.voteAverage.toString()
        tv_popularity.text = data.popularity.toString()
        tv_desc.text = data.overview

        ib_favorite.setOnClickListener {
            viewmodel.addTvShow(data)
            toast(getString(R.string.berhasil_ditambahkan))
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail_tv
    }
}
