package com.jeanbarrossilva.stoa.ui.compose.defaults

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

object BookComposerDefaults {
    fun Modifier.bookCover(): Modifier {
        return composed {
            requiredWidth(200.dp)
                .padding(20.dp)
                .aspectRatio(9f / 16f)
        }
    }
}