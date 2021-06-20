package com.jeanbarrossilva.stoa.ui.fragment

import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.stoa.extensions.context.activity.fab
import com.jeanbarrossilva.stoa.extensions.context.colorOf
import com.jeanbarrossilva.stoa.extensions.number.percentOf
import com.jeanbarrossilva.stoa.extensions.ui.activity.toolbar
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.model.repository.AuthorRepository
import com.jeanbarrossilva.stoa.presenter.HomePresenter
import com.jeanbarrossilva.stoa.presenter.view.HomeView
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.compose.HomeUI
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
            ?.setUpdateListener(onUpdate)
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

    override fun onResume() {
        super.onResume()
        configToolbar()
        activity?.fab?.show()
    }

    override fun assignViews() {
    }

    override fun configViews() {
    }

    override fun configToolbar() {
        activity?.toolbar?.androidToolbar?.setTitle(R.string.fragment_home)
    }

    override fun onInitialFabConfig() {
        context?.let { context ->
            activity?.fab?.setImageResource(R.drawable.ic_round_add_24)
            activity?.fab?.imageTintList = ColorStateList.valueOf(context colorOf android.R.attr.textColorPrimaryInverse)
            activity?.fab?.setBackgroundColor(context colorOf android.R.attr.textColorPrimary)
            activity?.fab?.setOnClickListener {
                goToComposer()
            }
        }
    }

    override fun onError(error: Throwable) {
    }

    override fun showBooks(books: List<Book>) {
        this.books = books
    }

    override fun onBookClick(view: View, book: Book) {
        var willGoToBookDetails = false

        activity?.fab?.hide()
        animateBookViewTransitionFor(view) {
            if (!willGoToBookDetails && currentPlayTime > 35L percentOf totalDuration) {
                willGoToBookDetails = true
                findNavController().navigate(HomeFragmentDirections.toDetailsOf(book))
            }
        }
    }

    override fun goToComposer() {
        findNavController().navigate(HomeFragmentDirections.compose())
    }
}