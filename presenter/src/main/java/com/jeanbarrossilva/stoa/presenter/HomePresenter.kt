package com.jeanbarrossilva.stoa.presenter

import com.jeanbarrossilva.stoa.model.Author.Companion.books
import com.jeanbarrossilva.stoa.model.schedulerprovider.MainSchedulerProvider
import com.jeanbarrossilva.stoa.model.schedulerprovider.MainSchedulerProvider.subscribeTo
import com.jeanbarrossilva.stoa.presenter.core.Presenter
import com.jeanbarrossilva.stoa.presenter.core.source.DataSource
import com.jeanbarrossilva.stoa.presenter.view.HomeView

class HomePresenter(override val view: HomeView, private val dataSource: DataSource): Presenter<HomeView> {
    override fun start() {
        super.start()
        MainSchedulerProvider.subscribeTo(
            dataSource.fetchAuthors(),
            onNext = { authors ->
                view.showBooks(authors.books)
            },
            onError = { error ->
                view.onError(error)
            }
        )
    }
}