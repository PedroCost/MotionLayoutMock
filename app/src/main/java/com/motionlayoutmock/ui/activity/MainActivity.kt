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
    var atStartButtonCancel2 = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvMotion.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvMotion.adapter = foodAdapter
        foodAdapter.addAll(movieDummyData2)


        binding.buttonFirst.setOnClickListener {
            //showOneTileAnim()
            makeFirstAnim()
        }

        binding.buttonSecond.setOnClickListener {
            //showFullTileAnim()
            makeSecondAnim()
        }

        binding.buttonCancelar.setOnClickListener {
            showCancelAnim()
        }

        binding.buttonCancelar2.setOnClickListener {
            showFullCancelAnim()
        }
    }

    private fun makeFirstAnim(){
        if (atStart) {
            atStart = false
            binding.mlRecyclerView.setTransition(R.id.startFirstPart, R.id.endFirstPart)
            binding.mlRecyclerView.transitionToEnd()
            binding.mlCancelButton.setTransition(R.id.startFirstCancelAnim, R.id.endFirstCancelAnim)
            binding.mlCancelButton.transitionToEnd()
        } else {
            atStart = true
            binding.mlRecyclerView.setTransition(R.id.endFirstPart, R.id.startFirstPart)
            binding.mlRecyclerView.transitionToEnd()
            binding.mlCancelButton.setTransition(R.id.endFirstCancelAnim, R.id.startFirstCancelAnim)
            binding.mlCancelButton.transitionToEnd()
        }
    }

    private fun makeSecondAnim() {
        if (atStart) return
        if (atStart2) {
            atStart2 = false
            binding.mlRecyclerView.setTransition(R.id.startSecondPart, R.id.endSecondPart)
            binding.mlRecyclerView.transitionToEnd()
            binding.mlCancelButton.setTransition(R.id.startSecondCancelAnim, R.id.endSecondCancelAnim)
            binding.mlCancelButton.transitionToEnd()
        } else {
            atStart2 = true
            binding.rvMotion.scrollToPosition(0)
            binding.mlRecyclerView.setTransition(R.id.endSecondPart, R.id.startSecondPart)
            binding.mlRecyclerView.transitionToEnd()
            binding.mlCancelButton.setTransition(R.id.endSecondCancelAnim, R.id.startSecondCancelAnim)
            binding.mlCancelButton.transitionToEnd()
        }
    }



    private fun showOneTileAnim() {
        if (atStart) {
            atStart = false
            binding.mlRecyclerView.setTransition(R.id.startFirstPart, R.id.endFirstPart)
            binding.mlRecyclerView.transitionToEnd()
        } else {
            atStart = true
            binding.mlRecyclerView.setTransition(R.id.endFirstPart, R.id.startFirstPart)
            binding.mlRecyclerView.transitionToEnd()
        }
    }

    private fun showFullTileAnim() {
        if (atStart) return
        if (atStart2) {
            atStart2 = false
            binding.mlRecyclerView.setTransition(R.id.startSecondPart, R.id.endSecondPart)
            binding.mlRecyclerView.transitionToEnd()
        } else {
            atStart2 = true
            binding.mlRecyclerView.setTransition(R.id.endSecondPart, R.id.startSecondPart)
            binding.mlRecyclerView.transitionToEnd()
        }
    }

    private fun showCancelAnim(){
        if (atStartButtonCancel) {
            atStartButtonCancel = false
            binding.mlCancelButton.setTransition(R.id.startFirstCancelAnim, R.id.endFirstCancelAnim)
            binding.mlCancelButton.transitionToEnd()
        } else {
            atStartButtonCancel = true
            binding.mlCancelButton.setTransition(R.id.endFirstCancelAnim, R.id.startFirstCancelAnim)
            binding.mlCancelButton.transitionToEnd()
        }
    }

    private fun showFullCancelAnim(){
        if (atStartButtonCancel) return
        if (atStartButtonCancel2) {
            atStartButtonCancel2 = false
            binding.mlCancelButton.setTransition(R.id.startSecondCancelAnim, R.id.endSecondCancelAnim)
            binding.mlCancelButton.transitionToEnd()
        } else {
            atStartButtonCancel2 = true
            binding.mlCancelButton.setTransition(R.id.endSecondCancelAnim, R.id.startSecondCancelAnim)
            binding.mlCancelButton.transitionToEnd()
        }
    }
}