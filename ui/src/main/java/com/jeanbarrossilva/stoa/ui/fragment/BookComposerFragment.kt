package com.jeanbarrossilva.stoa.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jeanbarrossilva.stoa.extensions.context.activity.fab
import com.jeanbarrossilva.stoa.extensions.uri.asBitmap
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.presenter.BookComposerPresenter
import com.jeanbarrossilva.stoa.presenter.view.BookComposerView
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.compose.BookComposerUI
import com.jeanbarrossilva.stoa.ui.viewmodel.BookComposerViewModel

class BookComposerFragment: Fragment(), BookComposerView {
    private val viewModel by viewModels<BookComposerViewModel>()
    private val coverPickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { data ->
        data?.asBitmap(context)?.let(viewModel::setCoverBitmap)
    }

    override val presenter = BookComposerPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                BookComposerUI(
                    this@BookComposerFragment,
                    viewModel
                )
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

    override fun onInitialFabConfig() {
        activity?.fab?.setImageResource(R.drawable.ic_round_done_24)
    }

    override fun onError(error: Throwable) {
    }

    override fun showAvailableImages() {
        coverPickerLauncher.launch("image/*")
    }

    override fun compose(): Book {
        // TODO: Compose using the provided data.
        return Book.empty
    }
}