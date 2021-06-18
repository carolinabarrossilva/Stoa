package com.jeanbarrossilva.stoa.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.stoa.extensions.view.imageview.load
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.ui.R

class BookViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val coverView = view.findViewById<ImageView>(R.id.cover_view)
    private val titleView = view.findViewById<TextView>(R.id.title_view)
    private val authorNameView = view.findViewById<TextView>(R.id.author_name_view)

    fun bind(book: Book, onBookClick: (View, Book) -> Unit) {
        itemView.setOnClickListener { view ->
            onBookClick(view, book)
        }
        coverView.contentDescription = itemView.context.getString(R.string.view_book_content_description_cover).format(
            book.author.name,
            book.title
        )
        coverView.load(book.cover.url)
        titleView.text = book.title
        authorNameView.text = book.author.name
    }
}