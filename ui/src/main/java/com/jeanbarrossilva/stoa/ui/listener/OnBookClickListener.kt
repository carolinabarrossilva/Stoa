package com.jeanbarrossilva.stoa.ui.listener

import com.jeanbarrossilva.stoa.model.Book

fun interface OnBookClickListener {
    fun onClick(book: Book)
}