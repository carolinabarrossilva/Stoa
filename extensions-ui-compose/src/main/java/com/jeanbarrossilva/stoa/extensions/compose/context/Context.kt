package com.jeanbarrossilva.stoa.extensions.compose.context

import android.content.Context
import androidx.annotation.AttrRes
import androidx.compose.ui.graphics.Color
import com.jeanbarrossilva.stoa.extensions.context.colorOf

infix fun Context.colorOf(@AttrRes attrRes: Int): Color {
    return Color(colorOf(attrRes))
}