package com.jeanbarrossilva.stoa.extensions.fragmenttransaction

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentTransaction
import com.jeanbarrossilva.stoa.extensions.any.doIf
import com.jeanbarrossilva.stoa.extensions.kclass.instantiate

inline fun <reified FF: FragmentFactory, reified F: Fragment> FragmentTransaction.replace(
    @IdRes containerId: Int,
    vararg args: Any?,
    willAddToBackStack: Boolean = true
):
    FragmentTransaction {
    val fragment = FF::class.instantiate<F>(*args)
    val tag = fragment::class.java.name
    return replace(containerId, fragment, tag).doIf(willAddToBackStack) {
        addToBackStack(null)
    }
}