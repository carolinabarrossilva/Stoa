package com.jeanbarrossilva.stoa.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.jeanbarrossilva.stoa.extensions.any.delayedBy
import com.jeanbarrossilva.stoa.extensions.number.dp
import com.jeanbarrossilva.stoa.extensions.view.imageview.load
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.presenter.BookDetailsPresenter
import com.jeanbarrossilva.stoa.presenter.view.BookDetailsView
import com.jeanbarrossilva.stoa.ui.R
import com.makeramen.roundedimageview.RoundedImageView

class BookDetailsFragment(override val book: Book): Fragment(R.layout.fragment_book_details), BookDetailsView {
    private lateinit var coverView: RoundedImageView
    private lateinit var authorNameView: TextView
    private lateinit var titleView: TextView
    private lateinit var subtitleView: TextView
    private lateinit var descriptionView: TextView
    private lateinit var buyButton: MaterialButton

    override val presenter = BookDetailsPresenter(this)

    private fun animateDetailsEntrance() {
        listOf(coverView, authorNameView, titleView, subtitleView, descriptionView).forEachIndexed { index, view ->
            val defaultTranslationY = view.translationY
            val defaultAlpha = view.alpha

            view.translationY += 20.dp(context)
            view.alpha = 0f
            delayedBy(if (index == 0) 0 else 200 + (index * 10L)) {
                activity?.runOnUiThread {
                    view.animate()
                        .setDuration(200)
                        .translationY(defaultTranslationY)
                        .alpha(defaultAlpha)
                        .start()
                }
            }
        }
    }

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
    }

    override fun onError(error: Throwable) {
    }

    override fun showDetails() {
        animateDetailsEntrance()
        coverView.load(book.cover.url)
        authorNameView.text = context?.getString(R.string.fragment_book_details_author)?.format(book.author.name)
        titleView.text = book.title
        subtitleView.text = book.subtitle
        descriptionView.text = book.description
    }

    override fun buy() {
    }
}