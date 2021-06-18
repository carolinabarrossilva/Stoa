package com.jeanbarrossilva.stoa.extensions.view.viewgroup

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

/**
 * Generates an ID for every children in this [ViewGroup] that has its ID set to [View.NO_ID].
 */
fun ViewGroup.identifyUnidentifiedChildren() {
    children.filter { view ->
        view.id == View.NO_ID
    }.forEach { view ->
        view.id = View.generateViewId()
    }
}