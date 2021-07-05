package com.motionlayoutmock.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.motionlayoutmock.R
import com.motionlayoutmock.databinding.ActivityMainBinding
import com.motionlayoutmock.model.movieDummyData2
import com.motionlayoutmock.ui.adapter.MovieAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var foodAdapter: MovieAdapter = MovieAdapter()
    var atStart = true
    var atStart2 = true
    var atStartButtonCancel = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvMotion.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvMotion.adapter = foodAdapter
        foodAdapter.addAll(movieDummyData2)


        binding.buttonFirst.setOnClickListener {
            showOneTileAnim()
        }

        binding.buttonSecond.setOnClickListener {
            showFullTileAnim()
        }

        binding.buttonCancelar.setOnClickListener {
            showCancelAnim()
        }
    }

    private fun showOneTileAnim() {
        if (atStart) {
            atStart = false
            binding.motionRV.setTransition(R.id.startFirstPart, R.id.endFirstPart)
            binding.motionRV.transitionToEnd()
        } else {
            atStart = true
            binding.motionRV.setTransition(R.id.endFirstPart, R.id.startFirstPart)
            binding.motionRV.transitionToEnd()
        }
    }

    private fun showFullTileAnim() {
        if (atStart) return
        if (atStart2) {
            atStart2 = false
            binding.motionRV.setTransition(R.id.startSecondPart, R.id.endSecondPart)
            binding.motionRV.transitionToEnd()
        } else {
            atStart2 = true
            binding.motionRV.setTransition(R.id.endSecondPart, R.id.startSecondPart)
            binding.motionRV.transitionToEnd()
        }
    }

    private fun showCancelAnim(){
        if (atStartButtonCancel) {
            atStartButtonCancel = false
            binding.motionRV.setTransition(R.id.startFirstCancelAnim, R.id.endFirstCancelAnim)
            binding.motionRV.transitionToEnd()
        } else {
            atStartButtonCancel = true
            binding.motionRV.setTransition(R.id.endFirstCancelAnim, R.id.startFirstCancelAnim)
            binding.motionRV.transitionToEnd()
        }
    }
}