package com.bezzo.moviecatalogue.features.movie

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezzo.core.base.*
import com.bezzo.core.extension.hide
import com.bezzo.core.extension.launchActivity
import com.bezzo.core.extension.show
import com.bezzo.core.extension.toast
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.adapter.MovieRVAdapter
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.features.detailMovie.DetailMovieActivity
import com.bezzo.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.ext.android.inject


class MovieFragment : BaseFragment() {

    private val viewModel: MovieViewModel by inject()
    private val adapter: MovieRVAdapter by inject()
    private val list = ArrayList<Movie>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        context?.let {
            val layoutManager = LinearLayoutManager(it)
            rv_movie.layoutManager = layoutManager
            rv_movie.adapter = adapter

            adapter.setOnClick(object : OnItemClickListener{
                override fun onItemClick(itemView: View, position: Int) {
                    launchActivity<DetailMovieActivity>{
                        putExtra(AppConstant.DATA_MOVIE, list[position])
                    }
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }
            })

            pb_movie.hide()
            rv_movie.show()
            mb_retry.hide()

            list.clear()
            list.addAll(viewModel.getMovie())
            adapter.setItem(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_movie
    }
}