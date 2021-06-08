package com.jeanbarrossilva.stoa.presenter

import com.jeanbarrossilva.stoa.presenter.core.Presenter
import com.jeanbarrossilva.stoa.presenter.view.BookDetailsView

class BookDetailsPresenter(override val view: BookDetailsView): Presenter<BookDetailsView> {
    override fun start() {
        super.start()
        view.showDetails()
    }

    override fun finish() {
    }
}