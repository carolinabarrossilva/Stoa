package com.jeanbarrossilva.stoa.presenter.view

import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.presenter.core.view.View

interface BookDetailsView: View {
    val book: Book

    fun showDetails()

    fun buy()
}