package com.jeanbarrossilva.stoa.extensions.compose.composition

import android.app.Activity
import androidx.compose.runtime.compositionLocalOf

val LocalActivity = compositionLocalOf<Activity?> {
    null
}