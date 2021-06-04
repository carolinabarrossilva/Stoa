package com.jeanbarrossilva.stoa.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.ui.R

class BookViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val coverView = view.findViewById<ImageView>(R.id.cover_view)
    private val titleView = view.findViewById<TextView>(R.id.title_view)
    private val authorNameView = view.findViewById<TextView>(R.id.author_name_view)

    fun bind(book: Book, onBookClick: (Book) -> Unit) {
        itemView.setOnClickListener {
            onBookClick(book)
        }
        coverView.contentDescription = itemView.context.getString(R.string.view_book_content_description_cover).format(
            book.author.name,
            book.title
        )
        Glide.with(itemView.context).load(book.cover.url).into(coverView)
        titleView.text = book.title
        authorNameView.text = book.author.name
    }
}