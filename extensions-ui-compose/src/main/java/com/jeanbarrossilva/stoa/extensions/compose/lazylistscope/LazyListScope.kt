package com.jeanbarrossilva.stoa.extensions.compose.lazylistscope

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
private fun VerticalSpacingOf(spacing: Dp) {
    Spacer(
        Modifier
            .height(spacing)
    )
}

fun <T> LazyListScope.items(
    items: List<T>,
    key: ((index: Int, T) -> Unit)? = null,
    topSpacing: Dp,
    bottomSpacing: Dp,
    content: @Composable (T) -> Unit
) {
    itemsIndexed(items, key) { index, item ->
        if (index == 0)
            VerticalSpacingOf(topSpacing)

        content(item)

        if (index == items.lastIndex)
            VerticalSpacingOf(bottomSpacing)
    }
}