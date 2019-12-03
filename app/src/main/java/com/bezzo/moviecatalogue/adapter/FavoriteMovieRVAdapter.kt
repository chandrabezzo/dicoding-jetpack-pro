package com.bezzo.moviecatalogue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bezzo.core.base.BaseHolder
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.model.Movie
import kotlinx.android.synthetic.main.item_rv_favorite.view.*
import kotlinx.android.synthetic.main.item_rv_movie.view.*
import kotlinx.android.synthetic.main.item_rv_movie.view.iv_profile
import kotlinx.android.synthetic.main.item_rv_movie.view.tv_desc
import kotlinx.android.synthetic.main.item_rv_movie.view.tv_judul

class FavoriteMovieRVAdapter constructor(private val context: Context, private val list: ArrayList<Movie>)
    : PagedListAdapter<Movie, FavoriteMovieRVAdapter.Item>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

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

    fun setItem(values: ArrayList<Movie>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<Movie>(itemView){

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
            itemView.iv_remove.setOnClickListener { listenerRemove.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: Movie) {
            itemView.tv_judul.text = "${model.title} (${model.releaseDate})"
            itemView.tv_desc.text = "${model.voteAverage}"
            val image = "https://image.tmdb.org/t/p/w185/${model.posterPath}"
            GlideApp.with(context).load(image).into(itemView.iv_profile)
        }
    }
}