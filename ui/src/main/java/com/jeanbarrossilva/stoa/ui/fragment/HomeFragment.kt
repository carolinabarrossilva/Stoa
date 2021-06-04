package com.jeanbarrossilva.stoa.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.model.repository.AuthorRepository
import com.jeanbarrossilva.stoa.presenter.HomePresenter
import com.jeanbarrossilva.stoa.presenter.view.HomeView
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.adapter.BookAdapter
import com.jeanbarrossilva.stoa.ui.view.SessionLayout

class HomeFragment: Fragment(R.layout.fragment_home), HomeView {
    override val presenter = HomePresenter(this) {
        AuthorRepository.getAuthors()
    }

    private lateinit var popularSessionLayout: SessionLayout
    private lateinit var popularSessionContentView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
    }

    override fun assignViews() {
        view?.let { view ->
            popularSessionLayout = view.findViewById(R.id.popular_session_layout)
            popularSessionContentView = view.findViewById(R.id.popular_session_content_view)
        }
    }

    override fun configViews() {
    }

    override fun onError(error: Throwable) {
    }

    override fun showBooks(books: List<Book>) {
        popularSessionContentView.adapter = BookAdapter(books, ::onBookClick)
    }

    override fun onBookClick(book: Book) {
    }
}