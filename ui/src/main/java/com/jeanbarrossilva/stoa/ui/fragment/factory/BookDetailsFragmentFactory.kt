package com.jeanbarrossilva.stoa.ui.fragment.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.ui.fragment.BookDetailsFragment

class BookDetailsFragmentFactory(private val book: Book): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return if (className == BookDetailsFragment::class.java.name) {
            BookDetailsFragment(book)
        } else {
            super.instantiate(classLoader, className)
        }
    }
}