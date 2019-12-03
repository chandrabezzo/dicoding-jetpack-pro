package com.bezzo.moviecatalogue.ui.favoriteMovie

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.extension.hide
import com.bezzo.core.extension.launchActivity
import com.bezzo.core.extension.show
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.adapter.FavoriteMovieRVAdapter
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.ui.detailMovie.DetailMovieActivity
import com.bezzo.moviecatalogue.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.koin.android.ext.android.inject

class FavoriteMovieFragment : BaseFragment() {

    private val viewmodel: MovieViewModel by inject()
    private val adapter: FavoriteMovieRVAdapter by inject()
    private val list = ArrayList<Movie>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let {
            val layoutManager = LinearLayoutManager(it)
            rv_movie.layoutManager = layoutManager
            rv_movie.adapter = adapter
            viewmodel.getFavoriteMovies().observe(this, movies)
            adapter.setOnClick(object : OnItemClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    launchActivity<DetailMovieActivity>{
                        putExtra(AppConstant.DATA_MOVIE, list[position])
                    }
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }
            })

            adapter.setOnRemove(object : OnItemClickListener{
                override fun onItemClick(itemView: View, position: Int) {
                    viewmodel.removeMovie(list[position].id)
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }
            })

            mb_retry.setOnClickListener { viewmodel.getFavoriteMovies().observe(this, movies) }
        }
    }

    private val movies = Observer<MutableList<Movie>> {
        rv_movie.show()
        mb_retry.hide()
        pb_movie.hide()

        list.clear()
        list.addAll(it)
        adapter.setItem(list)
        adapter.notifyDataSetChanged()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_favorite_movie
    }
}
