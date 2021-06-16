package com.jeanbarrossilva.stoa.presenter.core.view

import com.jeanbarrossilva.stoa.presenter.core.Presenter

interface View {
    val presenter: Presenter<*>

    fun assignViews()

    fun configViews()

    fun onInitialFabConfig() {
    }

    fun onLastFabConfig() {
    }

    fun onError(error: Throwable)
}