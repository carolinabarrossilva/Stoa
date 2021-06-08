package com.jeanbarrossilva.stoa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.viewholder.BookViewHolder

class BookAdapter(private val books: List<Book>, private val onBookClick: (View, Book) -> Unit):
    RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position], onBookClick)
    }

    override fun getItemCount(): Int {
        return books.size
    }
}