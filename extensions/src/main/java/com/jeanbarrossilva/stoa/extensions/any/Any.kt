package com.jeanbarrossilva.stoa.extensions.any

fun <T> T.doIf(condition: T.() -> Boolean, action: T.() -> T): T {
    return if (condition()) action() else this
}