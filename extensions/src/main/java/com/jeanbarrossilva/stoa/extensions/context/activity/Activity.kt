package com.jeanbarrossilva.stoa.extensions.context.activity

import android.app.Activity
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jeanbarrossilva.stoa.extensions.view.searchFor

private val Activity.contentView
    get() = window.decorView.findViewById<FrameLayout>(android.R.id.content)

val Activity.drawerLayout
    get() = view?.searchFor<DrawerLayout>()
val Activity.fab
    get() = view?.searchFor<FloatingActionButton>()
val Activity.view
    get() = contentView.children.firstOrNull()