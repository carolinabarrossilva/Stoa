package com.jeanbarrossilva.stoa.presenter.view

import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.presenter.core.view.View

interface BookDetailsView: View {
    fun buy(book: Book)
}