package com.jeanbarrossilva.stoa.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView
import com.jeanbarrossilva.stoa.extensions.number.dp

class BookDetailsScrollView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):
    ScrollView(context, attrs, defStyleAttr) {
    init {
        isVerticalFadingEdgeEnabled = true
        setFadingEdgeLength(200.dp(context))
    }

    override fun setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled: Boolean) {
    }

    override fun setVerticalFadingEdgeEnabled(verticalFadingEdgeEnabled: Boolean) {
        if (verticalFadingEdgeEnabled)
            super.setVerticalFadingEdgeEnabled(verticalFadingEdgeEnabled)
    }

    override fun getTopFadingEdgeStrength(): Float {
        return 0f
    }
}