package com.jeanbarrossilva.stoa.model.schedulerprovider

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

object MainSchedulerProvider: SchedulerProvider {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun <T> SchedulerProvider.subscribeTo(observable: Observable<T>, onNext: Consumer<T>, onError: Consumer<Throwable>):
        Disposable {
        return observable
            .subscribeOn(io())
            .observeOn(ui())
            .subscribe(onNext, onError)
    }
}