package com.jeanbarrossilva.stoa.extensions.kclass

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

inline fun <reified F: Fragment> KClass<out FragmentFactory>.instantiate(vararg args: Any?): F {
    return primaryConstructor!!.call(*args).instantiate(ClassLoader.getSystemClassLoader(), F::class.java.name) as F
}