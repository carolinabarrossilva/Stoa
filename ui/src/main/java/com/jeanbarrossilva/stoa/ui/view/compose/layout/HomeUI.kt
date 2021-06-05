package com.jeanbarrossilva.stoa.ui.view.compose.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.stoa.model.Author
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.adapter.BookAdapter
import com.jeanbarrossilva.stoa.ui.view.compose.component.Section

@Composable
fun HomeUI(books: List<Book>, modifier: Modifier = Modifier) {
    Surface(
        modifier
            .fillMaxSize()
    ) {
        Section(
            title = stringResource(R.string.SessionLayout_popular_title),
            description = stringResource(R.string.SessionLayout_popular_description),
            onActionButtonClick = {
            }
        ) {
            AndroidView(
                { context ->
                    RecyclerView(context)
                },
                Modifier
                    .fillMaxWidth()
            ) { view ->
                view.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
                view.adapter = BookAdapter(books) {
                }
            }
        }
    }
}

@Composable
@Preview
private fun HomeUI_Preview() {
    HomeUI(books = Author.sample.books)
}