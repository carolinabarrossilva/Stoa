package com.jeanbarrossilva.stoa.model

import java.io.Serializable

data class Book(
    val author: Author,
    val cover: BookCover,
    val title: String,
    val subtitle: String,
    val description: String
): Serializable {
    operator fun contains(other: CharSequence): Boolean {
        return author.name.contains(other, ignoreCase = true)
            || title.contains(other, ignoreCase = true)
            || subtitle.contains(other, ignoreCase = true)
    }
}