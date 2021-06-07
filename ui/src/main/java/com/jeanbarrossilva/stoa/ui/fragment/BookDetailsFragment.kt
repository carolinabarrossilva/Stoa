package com.jeanbarrossilva.stoa.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import com.jeanbarrossilva.stoa.extensions.view.imageview.load
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.presenter.BookDetailsPresenter
import com.jeanbarrossilva.stoa.presenter.view.BookDetailsView
import com.jeanbarrossilva.stoa.ui.R
import com.makeramen.roundedimageview.RoundedImageView

class BookDetailsFragment: Fragment(R.layout.fragment_book_details), BookDetailsView {
    private val navArgs by navArgs<BookDetailsFragmentArgs>()
    private val book: Book by lazy {
        navArgs.book
    }

    private lateinit var coverView: RoundedImageView
    private lateinit var authorNameView: TextView
    private lateinit var titleView: TextView
    private lateinit var subtitleView: TextView
    private lateinit var descriptionView: TextView
    private lateinit var buyButton: MaterialButton

    override val presenter = BookDetailsPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
    }

    override fun assignViews() {
        view?.let { view ->
            coverView = view.findViewById(R.id.cover_view)
            authorNameView = view.findViewById(R.id.author_name_view)
            titleView = view.findViewById(R.id.title_view)
            subtitleView = view.findViewById(R.id.subtitle_view)
            descriptionView = view.findViewById(R.id.description_view)
            buyButton = view.findViewById(R.id.buy_button)
        }
    }

    override fun configViews() {
        coverView.load(book.cover.url)
        authorNameView.text = context?.getString(R.string.fragment_book_details_author)?.format(book.author.name)
        titleView.text = book.title
        subtitleView.text = book.subtitle
        descriptionView.text = book.description
    }

    override fun onError(error: Throwable) {
    }

    override fun buy(book: Book) {
    }
}