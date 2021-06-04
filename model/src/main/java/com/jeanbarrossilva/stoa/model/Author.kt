package com.jeanbarrossilva.stoa.model

data class Author(val name: String) {
    val books = mutableListOf<Book>()

    companion object {
        val List<Author>.books
            get() = flatMap { author ->
                author.books
            }

        val sample = Author("André Silva").apply {
            val book = Book(
                author = this,
                cover = BookCover(url = "https://storage.googleapis.com/images.uiclap.com/capa/ua6632.jpg"),
                title = "Vá para onde a AÇÃO acontece!",
                subtitle = "Os 5 princípios de uma LIDERANÇA centrada no EXEMPLO"
            )
            books.add(book)
        }
    }
}