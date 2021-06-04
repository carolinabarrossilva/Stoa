package com.jeanbarrossilva.stoa.extensions.number

import android.content.Context
import android.util.TypedValue
import com.jeanbarrossilva.stoa.extensions.context.convert
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
@PublishedApi
internal fun <T : Number> Number.to(numberClass: KClass<T>): T {
    return when (numberClass) {
        Byte::class -> toByte()
        Float::class -> toFloat()
        Int::class -> toInt()
        Long::class -> toLong()
        Short::class -> toInt().toShort()
        else -> throw IllegalStateException()
    } as T
}

fun Number.dp(context: Context?): Int {
    return context?.convert(this to TypedValue.COMPLEX_UNIT_DIP) ?: 0
}

inline fun <reified T : Number> Number.to(): T {
    return to(T::class)
}