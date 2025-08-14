package com.d8corp.d8amelihovstest

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CollapsingCarouselNestedScrollConnection(val carouselMaxHeight: Int) : NestedScrollConnection {

    var topBarOffset: Int by mutableIntStateOf(0)
        private set

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val delta = available.y.toInt()
        val newOffset = topBarOffset + delta
        val previousOffset = topBarOffset
        topBarOffset = newOffset.coerceIn(-carouselMaxHeight, 0)
        val consumed = topBarOffset - previousOffset
        return Offset(0f, consumed.toFloat())
    }
}