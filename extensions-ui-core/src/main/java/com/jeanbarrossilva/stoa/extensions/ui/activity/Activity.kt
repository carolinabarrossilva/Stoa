package com.jeanbarrossilva.stoa.extensions.ui.activity

import android.app.Activity
import com.jeanbarrossilva.stoa.extensions.context.activity.view
import com.jeanbarrossilva.stoa.extensions.view.searchFor
import com.jeanbarrossilva.stoa.ui.core.view.StoaToolbar

val Activity.toolbar
    get() = view?.searchFor<StoaToolbar>()