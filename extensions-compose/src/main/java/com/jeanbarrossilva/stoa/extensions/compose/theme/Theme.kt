package com.jeanbarrossilva.stoa.extensions.compose.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.stoa.extensions.compose.colors.androidColors
import com.jeanbarrossilva.stoa.extensions.compose.typography.androidTypography

@Composable
fun AndroidTheme(
    colors: Colors = androidColors(),
    typography: Typography = androidTypography(),
    content: @Composable () -> Unit
) {
    MaterialTheme(colors, typography, content = content)
}