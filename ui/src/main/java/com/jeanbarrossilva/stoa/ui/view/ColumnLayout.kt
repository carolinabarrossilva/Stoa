package com.jeanbarrossilva.stoa.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.jeanbarrossilva.stoa.extensions.context.withStyledAttributes
import com.jeanbarrossilva.stoa.ui.R
import top.defaults.drawabletoolbox.DrawableBuilder

class ColumnLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {
    var spacing = 0
        set(spacing) {
            field = spacing
            dividerDrawable = DrawableBuilder().height(spacing).build()
        }

    init {
        config()
        getAttrs()
    }

    private fun config() {
        orientation = VERTICAL
        showDividers = SHOW_DIVIDER_MIDDLE
    }

    private fun getAttrs() {
        context.withStyledAttributes(attrs, defStyleAttr, R.styleable.ColumnLayout) { index ->
            if (index == R.styleable.ColumnLayout_spacing)
                spacing = getDimension(index, spacing.toFloat()).toInt()
        }
    }

    override fun setOrientation(orientation: Int) {
        if (orientation == VERTICAL)
            super.setOrientation(orientation)
    }
}