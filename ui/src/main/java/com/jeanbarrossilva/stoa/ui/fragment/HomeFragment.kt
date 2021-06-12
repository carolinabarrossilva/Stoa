package com.jeanbarrossilva.stoa.ui.fragment

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.jeanbarrossilva.stoa.extensions.fragmenttransaction.replace
import com.jeanbarrossilva.stoa.extensions.number.percentOf
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.model.repository.AuthorRepository
import com.jeanbarrossilva.stoa.presenter.HomePresenter
import com.jeanbarrossilva.stoa.presenter.view.HomeView
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.compose.HomeUI
import com.jeanbarrossilva.stoa.ui.fragment.factory.BookDetailsFragmentFactory
import com.makeramen.roundedimageview.RoundedImageView

class HomeFragment: Fragment(), HomeView {
    override val presenter = HomePresenter(this) {
        AuthorRepository.getAuthors()
    }
    var books by mutableStateOf(emptyList<Book>())

    private fun animateBookViewTransitionFor(view: View, onUpdate: ValueAnimator.() -> Unit) {
        view.findViewById<RoundedImageView>(R.id.cover_view)?.animate()
            ?.setDuration(150)
            ?.yBy(100f)
            ?.alpha(0f)
            ?.setUpdateListener { valueAnimator ->
                valueAnimator.onUpdate()
            }
            ?.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeUI(this@HomeFragment, books)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
    }

    override fun assignViews() {
    }

    override fun configViews() {
    }

    override fun onError(error: Throwable) {
    }

    override fun showBooks(books: List<Book>) {
        this.books = books
    }

    override fun onBookClick(view: View, book: Book) {
        var willGoToBookDetails = false
        animateBookViewTransitionFor(view) {
            parentFragmentManager.commit {
                if (!willGoToBookDetails && currentPlayTime > 35L percentOf totalDuration) {
                    setCustomAnimations(
                        R.anim.activity_open_enter,
                        R.anim.activity_close_exit,
                        R.anim.activity_open_enter,
                        R.anim.activity_close_exit
                    )
                    replace<BookDetailsFragmentFactory, BookDetailsFragment>(R.id.container, book)
                    willGoToBookDetails = true
                }
            }
        }
    }
}