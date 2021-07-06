package com.motionlayoutmock.ui.adapter

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.motionlayoutmock.R
import com.motionlayoutmock.databinding.ItemMovieBinding
import com.motionlayoutmock.databinding.ItemMovieWithTimerBinding
import com.motionlayoutmock.model.MovieModel

class MovieAdapterWithTimer : RecyclerView.Adapter<MovieAdapterWithTimer.MovieViewHolder>() {

    private var movieList: MutableList<MovieModel> = mutableListOf()

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val layoutInflater: View = if (viewType == 1) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_with_timer, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        }

        return MovieViewHolder(layoutInflater)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 1;
        else 2;
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val child = movieList[position]

        if (position == 0) {
            ItemMovieWithTimerBinding.bind(holder.itemView).apply {
                tvTitle.text = child.title
                tvDescription.text = child.description


                val totalTimeCountdown = 30
                val countDownTimer = object : CountDownTimer((totalTimeCountdown * 1000).toLong(), 500) {
                    @SuppressLint("SetTextI18n")
                    override fun onTick(leftTimeInMilliseconds: Long) {
                        val seconds = leftTimeInMilliseconds / 1000
                        val percentage = (seconds.toDouble() / totalTimeCountdown.toDouble() * 100).toInt()
                        circularTimer.progress = percentage
                        circularProgress.text = String.format("%02d", seconds % 60)
                    }

                    override fun onFinish() {
                        if (circularProgress.text.equals("00:00")) {
                            circularProgress.text = "End"
                        } else {
                            circularProgress.text = "End"
                        }
                    }
                }
                countDownTimer.start()
            }
        } else {
            ItemMovieBinding.bind(holder.itemView).apply {
                tvTitle.text = child.title
                tvDescription.text = child.description
            }
        }
    }

    fun addAll(genreList: List<MovieModel>) {
        this.movieList.clear()
        this.movieList.addAll(genreList)
        notifyDataSetChanged()
    }


//    private fun startTimer(item: ItemMovieWithTimerBinding) {
//        val totalTimeCountdown = 30
//        val countDownTimer = object : CountDownTimer((totalTimeCountdown * 1000).toLong(), 500) {
//            @SuppressLint("SetTextI18n")
//            override fun onTick(leftTimeInMilliseconds: Long) {
//                val seconds = leftTimeInMilliseconds / 1000
//                val percentage = (seconds.toDouble() / totalTimeCountdown.toDouble() * 100).toInt()
//                item.circularDeterminativePb.progress = percentage
//                item.progressTv.text = String.format("%02d", seconds % 60)
//            }
//
//            override fun onFinish() {
//                if (item.progressTv.text.equals("00:00")) {
//                    item.progressTv.text = "End"
//                } else {
//                    item.progressTv.text = "End"
//                }
//            }
//        }
//        countDownTimer.start()
//    }
}