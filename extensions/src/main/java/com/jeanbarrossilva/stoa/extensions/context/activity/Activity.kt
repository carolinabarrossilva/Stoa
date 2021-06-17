package com.jeanbarrossilva.stoa.extensions.context.activity

import android.app.Activity
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jeanbarrossilva.stoa.extensions.view.searchFor

private val Activity.contentView
    get() = window.decorView.findViewById<FrameLayout>(android.R.id.content)

val Activity.fab
    get() = view?.searchFor<FloatingActionButton>()
val Activity.toolbar
    get() = view?.searchFor<Toolbar>()
val Activity.view
    get() = contentView.children.firstOrNull()