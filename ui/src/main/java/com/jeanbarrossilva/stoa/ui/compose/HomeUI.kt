package com.jeanbarrossilva.stoa.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        SearchPageScaffold(
            title = stringResource(R.string.PageScaffold_home_title),
            searchQuery,
            onQueryChange = {
                searchQuery = it
            },
            modifier
        ) {
            Section(
                title = stringResource(R.string.Session_popular_title),
                onActionButtonClick = {
                },
                spacing = 20.dp,
                padding = 15.dp
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
@Preview(locale = "pt-rBR", showBackground = true)
private fun HomeUI_Preview() {
    HomeUI(books = Author.sample.books)
}