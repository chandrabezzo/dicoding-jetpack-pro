package com.bezzo.moviecatalogue.features.tvShow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.base.Error
import com.bezzo.core.base.Loading
import com.bezzo.core.base.Receive
import com.bezzo.core.base.ViewModelState
import com.bezzo.core.extension.hide
import com.bezzo.core.extension.launchActivity
import com.bezzo.core.extension.show
import com.bezzo.core.extension.toast
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.adapter.MovieRVAdapter
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.features.detailTv.DetailTvActivity
import com.bezzo.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.android.ext.android.inject

class TvShowFragment : BaseFragment() {

    private val viewModel: TvShowViewModel by inject()
    private val adapter: MovieRVAdapter by inject()
    private val list = ArrayList<Movie>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            val layoutManager = LinearLayoutManager(it)
            rv_tv.layoutManager = layoutManager
            rv_tv.adapter = adapter

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

            pb_tv.hide()
            rv_tv.show()
            mb_retry.hide()

            list.clear()
            list.addAll(viewModel.getTv())
            adapter.setItem(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_tv_show
    }
}
