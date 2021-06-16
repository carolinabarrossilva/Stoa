package com.jeanbarrossilva.stoa.model

import java.io.Serializable

data class BookCover(val url: String): Serializable {
    companion object {
        val empty = BookCover(url = "http://syndarma.org.br/2019/upload/thumbnail.png")
    }
}