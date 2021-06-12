package com.jeanbarrossilva.stoa.extensions.compose.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.jeanbarrossilva.stoa.extensions.compose.composition.LocalActivity
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

@Composable
fun isInPreview(): Boolean {
    return LocalView.current.isInEditMode
}

/**
 * Implementation of [com.jeanbarrossilva.stoa.extensions.view.onKeyboardEvent] that provides a [SoftwareKeyboardController] for
 * controlling the keyboard in Compose.
 *
 * @see com.jeanbarrossilva.stoa.extensions.view.onKeyboardEvent
 **/
@SuppressLint("ComposableNaming")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun onKeyboardEvent(action: SoftwareKeyboardController.(isOpen: Boolean) -> Unit) {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    KeyboardVisibilityEvent.registerEventListener(LocalActivity.current) { isOpen ->
        softwareKeyboardController?.action(isOpen)
    }
}