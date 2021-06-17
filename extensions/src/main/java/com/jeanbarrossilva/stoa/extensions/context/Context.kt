package com.jeanbarrossilva.stoa.extensions.context

import android.content.Context
import android.content.res.Configuration
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.StyleableRes
import androidx.core.content.res.use
import androidx.core.content.withStyledAttributes
import com.jeanbarrossilva.stoa.extensions.any.doIf
import com.jeanbarrossilva.stoa.extensions.number.to

val Context.isSystemInDarkTheme
    get() = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

@PublishedApi
internal inline infix fun <reified N: Number> Context.convert(conversion: Pair<Number, Int>): N {
    val (value, unit) = conversion
    return TypedValue.applyDimension(unit, value.toFloat(), resources.displayMetrics).to()
}

infix fun Context.colorOf(@AttrRes attrRes: Int): Int {
    return obtainStyledAttributes(intArrayOf(attrRes)).use {
        it.getColor(0, Color.TRANSPARENT)
    }.doIf({
        this == Color.TRANSPARENT
    }) {
        val typedValue = TypedValue()
        theme.resolveAttribute(attrRes, typedValue, true)
        typedValue.data
    }
}

infix fun Context.drawableOf(@AttrRes attrRes: Int): Drawable? {
    return obtainStyledAttributes(intArrayOf(attrRes)).use {
        it.getDrawable(0)
    }
}

infix fun Context.typefaceOf(@AttrRes attrRes: Int): Typeface? {
    return obtainStyledAttributes(intArrayOf(attrRes)).use {
        it.getFont(0)
    }
}

fun Context.withStyledAttributes(
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleableRes styleableRes: IntArray,
    onEachIndex: TypedArray.(index: Int) -> Unit
) {
    withStyledAttributes(attrs, styleableRes, defStyleAttr) {
        0.until(indexCount).forEach { index ->
            onEachIndex(index)
        }
    }
}