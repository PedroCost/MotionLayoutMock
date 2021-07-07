package com.motionlayoutmock.ui.activity

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.motionlayoutmock.R
import com.motionlayoutmock.databinding.ActivityAllAnimsBinding
import com.motionlayoutmock.model.movieDummyData2
import com.motionlayoutmock.ui.CustomLayout
import com.motionlayoutmock.ui.adapter.MovieAdapterWithTimer


class AllAnimsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllAnimsBinding
    private var movieAdapter: MovieAdapterWithTimer = MovieAdapterWithTimer(this)
    var atStart = true; var atStart2 = true

    lateinit var customLayout : CustomLayout

    private lateinit var linearSmoothScroller: LinearSmoothScroller
    private lateinit var scrollListener :RecyclerView.OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllAnimsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customLayout = CustomLayout(this, RecyclerView.HORIZONTAL, false)

        //binding.rvMotionAll.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvMotionAll.layoutManager = customLayout
        binding.rvMotionAll.adapter = movieAdapter
        movieAdapter.addAll(movieDummyData2)


        customLayout.setScrollEnabled(false)



        binding.buttonFirst.setOnClickListener {
            makeFirstAnim()
        }

        binding.buttonSecond.setOnClickListener {
            makeSecondAnim()
        }


        //Slow recycler view scrolling
        linearSmoothScroller =
            object : LinearSmoothScroller(binding.rvMotionAll.context) {
                val MILLISECONDS_PER_INCH = 100f
                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                    return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
                }
            }

        scrollListener = object : RecyclerView.OnScrollListener()
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)
                makeSecondAnim()
            }
        }
    }


    private fun makeFirstAnim() {
        if (!atStart2) return
        if (atStart) {
            atStart = false
            binding.mlAll.setTransition(R.id.startFirstAllAnim, R.id.endFirstAllAnim)
            binding.mlAll.transitionToEnd()
            binding.rvMotionAll.addOnScrollListener(scrollListener)
        } else {
            atStart = true
            binding.mlAll.setTransition(R.id.endFirstAllAnim, R.id.startFirstAllAnim)
            binding.mlAll.transitionToEnd()
        }
    }

    private fun makeSecondAnim() {
        if (atStart) return
        if (atStart2) {
            atStart2 = false
            binding.rvMotionAll.removeOnScrollListener(scrollListener)
            binding.mlAll.setTransition(R.id.startSecondAllAnim, R.id.endSecondAllAnim)
            binding.mlAll.transitionToEnd()

            customLayout.setScrollEnabled(true)

        } else {
            atStart2 = true
            //binding.rvMotionAll.smoothScrollToPosition(0) //Scroll to start of recycler view
            binding.rvMotionAll.addOnScrollListener(scrollListener)
            linearSmoothScroller.targetPosition = 0
            binding.rvMotionAll.layoutManager?.startSmoothScroll(linearSmoothScroller)

            binding.mlAll.setTransition(R.id.endSecondAllAnim, R.id.startSecondAllAnim)
            binding.mlAll.transitionToEnd()
        }
    }

    fun timeEnded() {
    }

}