package com.motionlayoutmock.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.motionlayoutmock.R
import com.motionlayoutmock.databinding.ItemMovieBinding
import com.motionlayoutmock.databinding.ItemMovieWithTimerBinding
import com.motionlayoutmock.model.MovieModel

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList: MutableList<MovieModel> = mutableListOf()

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(
            layoutInflater
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val child = movieList[position]

        ItemMovieBinding.bind(holder.itemView).apply {
            tvTitle.text = child.title
            tvDescription.text = child.description
        }
    }

    fun addAll(genreList: List<MovieModel>) {
        this.movieList.clear()
        this.movieList.addAll(genreList)
        notifyDataSetChanged()
    }
}