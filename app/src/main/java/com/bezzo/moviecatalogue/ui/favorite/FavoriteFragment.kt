package com.bezzo.moviecatalogue.ui.favorite


import android.os.Bundle
import android.view.View
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.extension.launchFragment
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.ui.favoriteMovie.FavoriteMovieFragment
import com.bezzo.moviecatalogue.ui.favoriteTvShow.FavoriteTvShowFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment() {

    override fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchFragment(R.id.fl_movie, FavoriteMovieFragment::class.java)

        tl_movie.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> launchFragment(R.id.fl_movie, FavoriteMovieFragment::class.java)
                    1 -> launchFragment(R.id.fl_movie, FavoriteTvShowFragment::class.java)
                }
            }
        })
    }

    override fun setLayout(): Int {
        return R.layout.fragment_favorite
    }
}
