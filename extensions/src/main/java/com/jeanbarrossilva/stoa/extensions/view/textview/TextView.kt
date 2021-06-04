package com.jeanbarrossilva.stoa.extensions.view.textview

import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat

fun TextView.setTypeface(@FontRes res: Int) {
    if (!isInEditMode)
        typeface = ResourcesCompat.getFont(context, res)
}