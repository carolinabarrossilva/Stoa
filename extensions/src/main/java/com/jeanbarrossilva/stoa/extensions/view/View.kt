package com.jeanbarrossilva.stoa.extensions.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
@PublishedApi
internal fun <V: View> View.search(viewClass: KClass<V>, isInclusive: Boolean): V? {
    return when {
        this::class == viewClass && isInclusive -> this as V
        this is ViewGroup -> children.filterIsInstance(viewClass.java).firstOrNull()
        else -> null
    }
}

/**
 * Executes [action] whenever the software keyboard is open or closed.
 * **/
fun View.onKeyboardEvent(action: (isOpen: Boolean) -> Unit) {
    KeyboardVisibilityEvent.setEventListener(context as Activity, action)
}

inline fun <reified V: View> View.searchFor(isInclusive: Boolean = true): V? {
    return search(V::class, isInclusive)
}