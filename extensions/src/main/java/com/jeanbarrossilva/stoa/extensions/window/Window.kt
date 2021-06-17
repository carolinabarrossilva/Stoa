package com.jeanbarrossilva.stoa.extensions.window

import android.view.Window
import androidx.core.view.WindowInsetsControllerCompat

/** Backwards-compatible version of [Window.getInsetsController]. **/
val Window.insetsControllerCompat
    get() = WindowInsetsControllerCompat(this, decorView)