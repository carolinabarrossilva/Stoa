package com.jeanbarrossilva.stoa.extensions.compose.fontfamily

import androidx.annotation.FontRes
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.toFontFamily

fun fontFamilyResource(@FontRes res: Int): FontFamily {
    return Font(res).toFontFamily()
}