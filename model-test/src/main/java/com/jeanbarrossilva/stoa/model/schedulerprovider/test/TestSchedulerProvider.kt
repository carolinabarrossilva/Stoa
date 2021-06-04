package com.jeanbarrossilva.stoa.model.schedulerprovider.test

import com.jeanbarrossilva.stoa.model.schedulerprovider.SchedulerProvider
import io.reactivex.rxjava3.core.Scheduler

class TestSchedulerProvider(private val scheduler: Scheduler): SchedulerProvider {
    override fun io(): Scheduler {
        return scheduler
    }

    override fun computation(): Scheduler {
        return scheduler
    }

    override fun ui(): Scheduler {
        return scheduler
    }
}