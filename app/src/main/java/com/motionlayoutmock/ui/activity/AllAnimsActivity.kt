package com.motionlayoutmock.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.motionlayoutmock.R
import com.motionlayoutmock.databinding.ActivityAllAnimsBinding
import com.motionlayoutmock.model.movieDummyData2
import com.motionlayoutmock.ui.adapter.MovieAdapter

class AllAnimsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllAnimsBinding
    private var foodAdapter: MovieAdapter = MovieAdapter()
    var atStart = true
    var atStart2 = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllAnimsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMotionAll.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvMotionAll.adapter = foodAdapter
        foodAdapter.addAll(movieDummyData2)

        binding.buttonFirst.setOnClickListener {
            makeFirstAnim()
        }

        binding.buttonSecond.setOnClickListener {
            makeSecondAnim()
        }
    }

    private fun makeFirstAnim(){
        if (!atStart2) return
        if (atStart) {
            atStart = false
            binding.mlAll.setTransition(R.id.startFirstAllAnim, R.id.endFirstAllAnim)
            binding.mlAll.transitionToEnd()

        } else {
            atStart = true
            binding.mlAll.setTransition(R.id.endFirstAllAnim, R.id.startFirstAllAnim)
            binding.mlAll.transitionToEnd()

        }
    }

    private fun makeSecondAnim(){
        if (atStart) return
        if (atStart2) {
            atStart2 = false
            binding.mlAll.setTransition(R.id.startSecondAllAnim, R.id.endSecondAllAnim)
            binding.mlAll.transitionToEnd()

        } else {
            atStart2 = true
            binding.rvMotionAll.smoothScrollToPosition(0)
            binding.mlAll.setTransition(R.id.endSecondAllAnim, R.id.startSecondAllAnim)
            binding.mlAll.transitionToEnd()
        }
    }

}