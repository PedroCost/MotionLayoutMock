package com.motionlayoutmock.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.motionlayoutmock.databinding.ActivityRoundTimerBinding


class RoundTimer : AppCompatActivity() {

    private lateinit var binding: ActivityRoundTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoundTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startTimer(1)
    }

    private fun startTimer(minuti: Int) {
        var countDownTimer = object : CountDownTimer((60 * minuti * 1000).toLong(), 500) {
            // 500 means, onTick function will be called at every 500 milliseconds
            @SuppressLint("SetTextI18n")
            override fun onTick(leftTimeInMilliseconds: Long) {
                val seconds = leftTimeInMilliseconds / 1000
                binding.circularDeterminativePb.progress = seconds.toInt()
                binding.progressTv.text = String.format("%02d", seconds % 60)
                // format the textview to show the easily readable format
            }

            override fun onFinish() {
                if (binding.progressTv.text.equals("00:00")) {
                    binding.progressTv.text = "End"
                } else {
                    binding.progressTv.text = "End"
                    // binding.progressBar.progress = 60 * minuti
                }
            }
        }
        countDownTimer.start()
    }
}