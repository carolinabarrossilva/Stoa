package com.jeanbarrossilva.stoa.extensions.view

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
@PublishedApi
internal fun <V: View> View.search(viewClass: KClass<V>, isInclusive: Boolean): V? {
    var foundView: V? = null
    if (this::class == viewClass && isInclusive)
        foundView = this as V
    else if (this is ViewGroup)
        for (child in children)
            if (child::class == viewClass)
                foundView = child as V
            else if (child is ViewGroup)
                foundView = child.search(viewClass, isInclusive)
    return foundView
}

inline fun <reified V: View> View.searchFor(isInclusive: Boolean = true): V? {
    return search(V::class, isInclusive)
}