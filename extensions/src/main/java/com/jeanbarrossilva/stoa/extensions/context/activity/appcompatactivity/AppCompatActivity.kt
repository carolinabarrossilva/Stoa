package com.jeanbarrossilva.stoa.extensions.context.activity.appcompatactivity

import androidx.appcompat.app.AppCompatActivity

val AppCompatActivity.previousFragment
    get() = with(supportFragmentManager) {
        primaryNavigationFragment?.parentFragmentManager?.fragments?.lastOrNull() ?: fragments.elementAtOrNull(fragments.lastIndex - 1)
    }