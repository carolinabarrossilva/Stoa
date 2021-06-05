package com.jeanbarrossilva.stoa.extensions.view.imgeview

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String) {
    if (!isInEditMode)
        Glide.with(this).load(url).into(this)
}