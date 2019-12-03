package com.bezzo.moviecatalogue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bezzo.core.base.BaseHolder
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.model.TvShow
import kotlinx.android.synthetic.main.item_rv_favorite.view.*
import kotlinx.android.synthetic.main.item_rv_movie.view.*
import kotlinx.android.synthetic.main.item_rv_movie.view.iv_profile
import kotlinx.android.synthetic.main.item_rv_movie.view.tv_desc
import kotlinx.android.synthetic.main.item_rv_movie.view.tv_judul

class FavoriteTvShowRVAdapter constructor(private val context: Context, private val list: ArrayList<TvShow>)
    : RecyclerView.Adapter<FavoriteTvShowRVAdapter.Item>(){

    lateinit var listener: OnItemClickListener
    lateinit var listenerRemove: OnItemClickListener

    fun setOnClick(listener: OnItemClickListener){
        this.listener = listener
    }

    fun setOnRemove(listener: OnItemClickListener){
        this.listenerRemove = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_favorite, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    fun setItem(values: ArrayList<TvShow>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<TvShow>(itemView){

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
            itemView.iv_remove.setOnClickListener { listenerRemove.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: TvShow) {
            itemView.tv_judul.text = "${model.name} (${model.firstAirDate})"
            itemView.tv_desc.text = "${model.voteAverage}"
            val image = "https://image.tmdb.org/t/p/w185/${model.posterPath}"
            GlideApp.with(context).load(image).into(itemView.iv_profile)
        }
    }
}