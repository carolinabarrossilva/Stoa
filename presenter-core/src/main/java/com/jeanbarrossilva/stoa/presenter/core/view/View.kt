package com.jeanbarrossilva.stoa.presenter.core.view

import com.jeanbarrossilva.stoa.presenter.core.Presenter

interface View {
    val presenter: Presenter<*>

    fun assignViews()

    fun configViews()

    fun onError(error: Throwable)
}