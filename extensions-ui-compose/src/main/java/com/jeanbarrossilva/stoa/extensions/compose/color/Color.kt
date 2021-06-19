package com.jeanbarrossilva.stoa.extensions.compose.color

import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor

fun colorOf(hex: String): Color {
    return Color(AndroidColor.parseColor(hex))
}