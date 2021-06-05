package com.jeanbarrossilva.stoa.extensions.compose.typography

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Typeface
import com.jeanbarrossilva.stoa.extensions.context.typefaceOf

@Composable
fun androidTypography(): Typography {
    val defaultFont = Typeface(LocalContext.current.typefaceOf(android.R.attr.fontFamily)!!).fontFamily!!
    return Typography(defaultFont)
}