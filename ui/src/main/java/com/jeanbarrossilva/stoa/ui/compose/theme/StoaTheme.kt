package com.jeanbarrossilva.stoa.ui.compose.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeanbarrossilva.stoa.extensions.compose.fontfamily.fontFamilyResource
import com.jeanbarrossilva.stoa.extensions.compose.modifier.doIf
import com.jeanbarrossilva.stoa.extensions.compose.theme.AndroidTheme
import com.jeanbarrossilva.stoa.ui.R

@Composable
private fun StoaTheme(modifier: Modifier = Modifier, fills: Boolean, content: @Composable () -> Unit) {
    AndroidTheme(Typography(fontFamilyResource(R.font.android_euclid_medium))) {
        Surface(
            modifier.doIf(fills) {
                fillMaxSize()
            },
            content = content
        )
    }
}

@Composable
fun StoaTheme(modifier: Modifier, content: @Composable () -> Unit) {
    StoaTheme(modifier, fills = true, content = content)
}

@Composable
fun StoaTheme(content: @Composable () -> Unit) {
    StoaTheme(fills = false, content = content)
}