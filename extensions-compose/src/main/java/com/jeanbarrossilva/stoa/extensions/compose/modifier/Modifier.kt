package com.jeanbarrossilva.stoa.extensions.compose.modifier

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.doIf(condition: Boolean, action: @Composable Modifier.() -> Modifier): Modifier {
    return if (condition) composed(factory = action) else this
}