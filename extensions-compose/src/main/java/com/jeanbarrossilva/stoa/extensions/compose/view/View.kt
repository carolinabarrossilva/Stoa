package com.jeanbarrossilva.stoa.extensions.compose.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

@Composable
fun isInPreview(): Boolean {
    return LocalView.current.isInEditMode
}