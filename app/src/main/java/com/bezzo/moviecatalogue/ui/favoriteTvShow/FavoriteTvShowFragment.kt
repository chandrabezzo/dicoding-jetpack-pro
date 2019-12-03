package com.bezzo.moviecatalogue.ui.favoriteTvShow

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
import com.bezzo.moviecatalogue.adapter.FavoriteTvShowRVAdapter
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.TvShow
import com.bezzo.moviecatalogue.ui.detailTv.DetailTvActivity
import com.bezzo.moviecatalogue.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import org.koin.android.ext.android.inject

class FavoriteTvShowFragment : BaseFragment() {

    private val viewmodel: TvShowViewModel by inject()
    private val adapter: FavoriteTvShowRVAdapter by inject()
    private val list = ArrayList<TvShow>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let {
            val layoutManager = LinearLayoutManager(it)
            rv_tv.layoutManager = layoutManager
            rv_tv.adapter = adapter
            viewmodel.getFavoriteTvShow().observe(this, tvShows)
            adapter.setOnClick(object : OnItemClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    launchActivity<DetailTvActivity>{
                        putExtra(AppConstant.DATA_TV, list[position])
                    }
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }
            })

            adapter.setOnRemove(object : OnItemClickListener{
                override fun onItemClick(itemView: View, position: Int) {
                    viewmodel.removeTvShow(list[position].id)
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }
            })

            mb_retry.setOnClickListener { viewmodel.getFavoriteTvShow().observe(this, tvShows) }
        }
    }

    private val tvShows = Observer<MutableList<TvShow>> {
        rv_tv.show()
        mb_retry.hide()
        pb_tv.hide()

        list.clear()
        list.addAll(it)
        adapter.setItem(list)
        adapter.notifyDataSetChanged()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_favorite_tv_show
    }
}
