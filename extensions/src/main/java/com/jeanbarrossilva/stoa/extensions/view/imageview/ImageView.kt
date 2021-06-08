package com.jeanbarrossilva.stoa.extensions.view.imageview

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

fun ImageView.load(url: String) {
    if (!isInEditMode)
        Glide.with(this).load(url).override(Target.SIZE_ORIGINAL).into(this)
}