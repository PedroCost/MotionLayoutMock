package com.motionlayoutmock.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.opengl.Visibility
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.motionlayoutmock.R
import com.motionlayoutmock.databinding.ItemMovieBinding
import com.motionlayoutmock.databinding.ItemMovieWithTimerBinding
import com.motionlayoutmock.model.MovieModel
import com.motionlayoutmock.ui.activity.AllAnimsActivity

class MovieAdapterWithTimer(context: Context) : RecyclerView.Adapter<MovieAdapterWithTimer.MovieViewHolder>() {

    private var movieList: MutableList<MovieModel> = mutableListOf()

    var isStarted = false
    var contexto = context

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /*
     * If its the first element of the list returns a LayoutInflater of type item_movie_with_timer,
     * for the rest of the list uses a LayoutInflater of type item_movie.
     */
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
        return if (position == 0) 1 // If its the first, to be used in onCreateViewHolder
        else 2;
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val child = movieList[position]

        if (position == 0) {
            ItemMovieWithTimerBinding.bind(holder.itemView).apply {
                includeMovie.tvTitle.text = child.title
                includeMovie.tvDescription.text = child.description

                timer(this)
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

    fun timer(binding: ItemMovieWithTimerBinding) {
        if (isStarted) return
        val countDownTimer = object : CountDownTimer((TOTAL_COUNTDOWN_TIME * 1000).toLong(), 500) {
            @SuppressLint("SetTextI18n")
            override fun onTick(leftTimeInMilliseconds: Long) {
                isStarted = true
                val seconds = leftTimeInMilliseconds / 1000
                val percentage = (seconds.toDouble() / TOTAL_COUNTDOWN_TIME.toDouble() * 100).toInt()
                binding.circularTimer.progress = percentage
                binding.circularProgress.text = String.format("%02d", seconds % 60)
            }

            override fun onFinish() {
                binding.circularProgress.text = "00"
                // Toast.makeText(context, "Time Ended", Toast.LENGTH_SHORT).show()

                //contexto.timeEnded()
            }
        }
        countDownTimer.start()
    }

    fun hideTimer(binding: ItemMovieWithTimerBinding){
        binding.circularProgress.visibility = View.INVISIBLE
        binding.circularTimer.visibility = View.INVISIBLE
    }

    companion object{
        private const val TOTAL_COUNTDOWN_TIME = 30
    }

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
