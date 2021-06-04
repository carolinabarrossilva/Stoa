package com.jeanbarrossilva.stoa.model.repository

import com.jeanbarrossilva.stoa.model.Author
import io.reactivex.rxjava3.core.Observable

object AuthorRepository {
    fun getAuthors(): Observable<List<Author>> {
        return Observable.just(listOf(Author.sample))
    }
}