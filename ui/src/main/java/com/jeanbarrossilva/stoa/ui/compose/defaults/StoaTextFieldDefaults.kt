package com.jeanbarrossilva.stoa.ui.compose.defaults

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jeanbarrossilva.stoa.extensions.compose.color.colorOf

object StoaTextFieldDefaults {
    val colors @Composable get() = textFieldColors(
        backgroundColor = if (isSystemInDarkTheme()) colorOf("#303030") else colorOf("#F9F9F9"),
        cursorColor = MaterialTheme.colors.onSurface,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
}