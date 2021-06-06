package com.jeanbarrossilva.stoa.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
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
import com.jeanbarrossilva.stoa.ui.compose.component.SearchPageScaffold
import com.jeanbarrossilva.stoa.ui.compose.component.Section
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@Composable
fun HomeUI(books: List<Book>, modifier: Modifier = Modifier) {
    var searchQuery by remember {
        mutableStateOf("")
    }

    StoaTheme {
        SearchPageScaffold(title = "Home", searchQuery, onQueryChange = {
            searchQuery = it
        }) {
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
}

@Composable
@Preview(showBackground = true)
private fun HomeUI_Preview() {
    HomeUI(books = Author.sample.books)
}