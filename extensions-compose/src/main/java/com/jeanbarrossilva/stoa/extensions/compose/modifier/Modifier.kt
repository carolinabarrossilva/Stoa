package com.jeanbarrossilva.stoa.extensions.compose.modifier

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalSoftwareKeyboardController


/**
 * Attaches the focus to the visibility of the keyboard, so that when focus is gained, the keyboard opens, and when it's lost, the
 * keyboard is closed.
 **/
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.attachFocusToKeyboardVisibility(
    focusRequester: FocusRequester = FocusRequester(),
    onFocusChanged: (isFocused: Boolean) -> Unit = {
    }
): Modifier {
    return composed {
        val softwareKeyboardController = LocalSoftwareKeyboardController.current
        focusRequester(focusRequester)
            .onFocusChanged { isFocused: Boolean ->
                if (isFocused) {
                    softwareKeyboardController?.show()
                    focusRequester.requestFocus()
                } else {
                    softwareKeyboardController?.hide()
                    focusRequester.freeFocus()
                }
                onFocusChanged(isFocused)
            }
    }
}

fun Modifier.doIf(condition: Boolean, action: @Composable Modifier.() -> Modifier): Modifier {
    return if (condition) composed(factory = action) else this
}

fun Modifier.withPlaceable(measure: Placeable.(scope: MeasureScope) -> MeasureResult): Modifier {
    return composed {
        layout { measurable, constraints ->
            measurable.measure(constraints).let { placeable ->
                placeable.measure(this)
            }
        }
    }
}

fun Modifier.onFocusChanged(action: (isFocused: Boolean) -> Unit): Modifier {
    return composed {
        onFocusChanged { state ->
            action(state.isFocused)
        }
    }
}