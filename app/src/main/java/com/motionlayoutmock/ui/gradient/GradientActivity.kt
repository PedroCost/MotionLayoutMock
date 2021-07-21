package com.motionlayoutmock.ui.gradient

import android.R.attr.*
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.motionlayoutmock.R
import com.motionlayoutmock.databinding.ActivityGradientBinding


class GradientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGradientBinding
    private lateinit var backAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGradientBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val colors: IntArray = intArrayOf(Color.parseColor("#C9000000"), Color.parseColor("#00EE82EE"))

        binding.buttonDiag.setOnClickListener {
            val gd = GradientDrawable(GradientDrawable.Orientation.BR_TL, colors)
            binding.clBackground.background = gd
        }

        binding.buttonBot.setOnClickListener {
            val gd = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors)
            binding.clBackground.background = gd
        }

        binding.buttonHide.setOnClickListener {
            val colors2: IntArray = intArrayOf(Color.parseColor("#00EE82EE"), Color.parseColor("#00EE82EE"))
            val gd = GradientDrawable(GradientDrawable.Orientation.BL_TR, colors2)
            binding.clBackground.background = gd
        }

    }
}