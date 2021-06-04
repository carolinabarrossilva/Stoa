package com.jeanbarrossilva.stoa.presenter.core.source

import com.jeanbarrossilva.stoa.model.Author
import io.reactivex.rxjava3.core.Observable

fun interface DataSource {
    fun fetchAuthors(): Observable<List<Author>>
}