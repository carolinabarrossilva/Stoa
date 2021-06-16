package com.jeanbarrossilva.stoa.presenter.core

import com.jeanbarrossilva.stoa.presenter.core.view.View

interface Presenter<V: View> {
    val view: V

    fun start() {
        view.assignViews()
        view.configViews()
        view.onInitialFabConfig()
    }

    fun finish() {
        view.onLastFabConfig()
    }
}