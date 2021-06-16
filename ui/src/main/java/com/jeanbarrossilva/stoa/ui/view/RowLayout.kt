package com.jeanbarrossilva.stoa.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.jeanbarrossilva.stoa.extensions.context.withStyledAttributes
import com.jeanbarrossilva.stoa.ui.R
import top.defaults.drawabletoolbox.DrawableBuilder

class RowLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {
    var spacing = 0
        set(spacing) {
            field = spacing
            dividerDrawable = DrawableBuilder().width(spacing).build()
        }

    init {
        config()
        getAttrs()
    }

    private fun config() {
        showDividers = SHOW_DIVIDER_MIDDLE
    }

    private fun getAttrs() {
        context.withStyledAttributes(attrs, defStyleAttr, R.styleable.RowLayout) { index ->
            if (index == R.styleable.RowLayout_spacing)
                spacing = getDimension(index, spacing.toFloat()).toInt()
        }
    }

    override fun setOrientation(orientation: Int) {
        if (orientation == HORIZONTAL)
            super.setOrientation(orientation)
    }
}