package com.jeanbarrossilva.stoa.extensions.any

import java.util.*
import kotlin.concurrent.timerTask

fun <T> T.doIf(condition: Boolean, action: T.() -> T): T {
    return if (condition) action() else this
}

fun <T> T.doIf(condition: T.() -> Boolean, action: T.() -> T): T {
    return doIf(condition(), action)
}

fun delayedBy(delay: Long, action: TimerTask.() -> Unit) {
    Timer().schedule(timerTask(action), delay)
}