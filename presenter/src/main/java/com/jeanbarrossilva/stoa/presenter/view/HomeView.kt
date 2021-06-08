package com.jeanbarrossilva.stoa.presenter.view

import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.presenter.core.view.View

interface HomeView: View {
    fun showBooks(books: List<Book>)

    fun onBookClick(view: android.view.View, book: Book)
}