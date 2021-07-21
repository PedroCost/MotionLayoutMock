package com.motionlayoutmock.ui.activity

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.motionlayoutmock.R
import com.motionlayoutmock.databinding.ActivityRecyclerViewBinding
import com.motionlayoutmock.model.movieDummyData2
import com.motionlayoutmock.ui.CustomLayout
import com.motionlayoutmock.ui.adapter.MovieAdapterWithTimer


class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding
    private var movieAdapter: MovieAdapterWithTimer = MovieAdapterWithTimer(this)
    lateinit var customLayout : CustomLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customLayout = CustomLayout(this, RecyclerView.HORIZONTAL, false)

        binding.bingeWidgetRail.layoutManager = customLayout
        binding.bingeWidgetRail.adapter = movieAdapter
        movieAdapter.addAll(movieDummyData2)


        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val percent = (binding.guideline.layoutParams as ConstraintLayout.LayoutParams).guidePercent
        val pad = width * percent
        binding.bingeWidgetRail.setPadding(pad.toInt(),0,0,0)



    }
}