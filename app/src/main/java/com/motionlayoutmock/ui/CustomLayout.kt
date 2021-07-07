package com.motionlayoutmock.ui

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class CustomLayout(context: Context, orientation: Int, reverse: Boolean) : LinearLayoutManager(context, orientation, reverse) {

    private var isScrollEnabled = true

    fun setScrollEnabled(flag: Boolean) {
        this.isScrollEnabled = flag
    }

    override fun canScrollHorizontally(): Boolean {
        return isScrollEnabled && super.canScrollHorizontally()
    }

}