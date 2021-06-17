package com.jeanbarrossilva.stoa.ui.activity

import android.os.Bundle
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.core.activity.StoaActivity

class MainActivity: StoaActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}