package com.jeanbarrossilva.stoa.extensions.compose.any

import androidx.compose.runtime.Composable

@Composable
fun <T> T.doIf(condition: Boolean, action: @Composable T.() -> T): T {
    return if (condition) this else action()
}