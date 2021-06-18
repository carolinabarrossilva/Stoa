package com.jeanbarrossilva.stoa.extensions.view.viewgroup.cardview

import androidx.annotation.Px
import androidx.cardview.widget.CardView

fun CardView.setContentPadding(@Px size: Int) {
    setContentPadding(size, size, size, size)
}