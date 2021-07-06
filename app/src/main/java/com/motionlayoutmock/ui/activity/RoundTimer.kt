package com.motionlayoutmock.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.motionlayoutmock.databinding.ActivityRoundTimerBinding


class RoundTimer : AppCompatActivity() {

    private lateinit var binding: ActivityRoundTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoundTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startTimer(30)
    }

    private fun startTimer(timeSeconds: Int) {
        val countDownTimer = object : CountDownTimer((timeSeconds * 1000).toLong(), 500) {
            // 500 means, onTick function will be called at every 500 milliseconds
            @SuppressLint("SetTextI18n")
            override fun onTick(leftTimeInMilliseconds: Long) {
                val seconds = leftTimeInMilliseconds / 1000
                val percentage = (seconds.toDouble() / timeSeconds.toDouble() * 100).toInt()
                binding.circularDeterminativePb.progress = percentage
                binding.progressTv.text = String.format("%02d", seconds % 60)
                // format the textview to show the easily readable format
            }

            override fun onFinish() {
                if (binding.progressTv.text.equals("00:00")) {
                    binding.progressTv.text = "End"
                } else {
                    binding.progressTv.text = "End"
                }
            }
        }
        countDownTimer.start()
    }
}