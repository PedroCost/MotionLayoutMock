package com.motionlayoutmock.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.motionlayoutmock.R
import com.motionlayoutmock.databinding.ActivityAllAnims2Binding
import com.motionlayoutmock.model.movieDummyData2
import com.motionlayoutmock.ui.CustomLayout
import com.motionlayoutmock.ui.adapter.MovieAdapterWithTimer


class AllAnimsActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityAllAnims2Binding
    private var movieAdapter: MovieAdapterWithTimer = MovieAdapterWithTimer(this)
    var atStart = true; var atStart2 = true
    lateinit var customLayout : CustomLayout
    private lateinit var linearSmoothScroller: LinearSmoothScroller
    private lateinit var scrollListener :RecyclerView.OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllAnims2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        var contx = this

        customLayout = CustomLayout(this, RecyclerView.HORIZONTAL, false)

        //binding.rvMotionAll.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.includedRail.bingeWidgetRail.layoutManager = customLayout
        binding.includedRail.bingeWidgetRail.adapter = movieAdapter
        movieAdapter.addAll(movieDummyData2)

        customLayout.setScrollEnabled(false)

        binding.buttonFirst.setOnClickListener {
            makeFirstAnim()
        }

        binding.buttonSecond.setOnClickListener {
            makeSecondAnim()
        }




        // If User AFK from list for TOTAL_COUNTDOWN_TIME seconds
        val countDownTimer = object : CountDownTimer((TOTAL_AFK_TIME * 1000).toLong(), 500) {
            override fun onTick(leftTimeInMilliseconds: Long) {}
            override fun onFinish() {
                Toast.makeText(contx, "scroll time ended", Toast.LENGTH_SHORT).show()
            }
        }
        binding.includedRail.bingeWidgetRail.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                countDownTimer.start()
            }
        })

        //Slow recycler view scrolling
        linearSmoothScroller =
            object : LinearSmoothScroller(binding.includedRail.bingeWidgetRail.context) {
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
            makeAnimation(R.id.startAnimRailPeak2, R.id.endAnimRailPeak2)

            //binding.rvMotionAll.addOnScrollListener(scrollListener)
        } else {
            atStart = true
            makeAnimation(R.id.endAnimRailPeak2, R.id.startAnimRailPeak2)
        }
    }

    private fun makeSecondAnim() {
        if (atStart) return
        if (atStart2) {
            atStart2 = false
            makeAnimation(R.id.endAnimRailPeak, R.id.endAnimShowFullRail)
            customLayout.setScrollEnabled(true)
            //binding.rvMotionAll.removeOnScrollListener(scrollListener)
        } else {
            atStart2 = true
            makeAnimation(R.id.endAnimShowFullRail, R.id.endAnimRailPeak)

            //binding.rvMotionAll.smoothScrollToPosition(0) //Scroll to start of recycler view
            //binding.rvMotionAll.addOnScrollListener(scrollListener)

            linearSmoothScroller.targetPosition = 0
            binding.includedRail.bingeWidgetRail.layoutManager?.startSmoothScroll(linearSmoothScroller)
            customLayout.setScrollEnabled(false)
        }
    }

    fun timeEnded() {
        Toast.makeText(this, "Starting First Element", Toast.LENGTH_SHORT).show()
    }

    private fun makeAnimation(transitionStart: Int, transitionEnd: Int){
        with(binding.bingeWidgetMotionLayout2){
            setTransition(transitionStart, transitionEnd)
            setTransitionDuration(ANIMATION_DURATION)
            transitionToEnd()
        }
    }

    companion object{
        private const val ANIMATION_DURATION = 1000
        private const val TOTAL_AFK_TIME = 10
    }

}