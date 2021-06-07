package com.jeanbarrossilva.stoa.ui.fragment

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
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.model.repository.AuthorRepository
import com.jeanbarrossilva.stoa.presenter.HomePresenter
import com.jeanbarrossilva.stoa.presenter.view.HomeView
import com.jeanbarrossilva.stoa.ui.compose.HomeUI

class HomeFragment: Fragment(), HomeView {
    private var books by mutableStateOf(emptyList<Book>())

    override val presenter = HomePresenter(this) {
        AuthorRepository.getAuthors()
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

    override fun onBookClick(book: Book) {
        findNavController().navigate(HomeFragmentDirections.toDetailsOf(book))
    }
}