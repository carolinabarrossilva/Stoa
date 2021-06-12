package com.jeanbarrossilva.stoa.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.stoa.extensions.compose.composition.LocalActivity
import com.jeanbarrossilva.stoa.model.Author
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.adapter.BookAdapter
import com.jeanbarrossilva.stoa.ui.compose.component.SearchPageScaffold
import com.jeanbarrossilva.stoa.ui.compose.component.Section
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme
import com.jeanbarrossilva.stoa.ui.fragment.HomeFragment

@Composable
fun HomeUI(fragment: HomeFragment, books: List<Book>, modifier: Modifier = Modifier) {
    var searchQuery by remember {
        mutableStateOf("")
    }

    CompositionLocalProvider(LocalActivity provides fragment.activity) {
        StoaTheme(modifier) {
            SearchPageScaffold(
                title = stringResource(R.string.PageScaffold_home_title),
                searchQuery,
                onQueryChange = {
                    searchQuery = it
                },
                searchContent = {
                    Box(
                        Modifier
                            .fillMaxSize()
                    ) {
                        Text("Content")
                    }
                }
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
                        view.adapter = BookAdapter(books) { bookView, book ->
                            fragment.onBookClick(bookView, book)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(locale = "pt-rBR", showBackground = true)
private fun HomeUI_Preview() {
    HomeUI(
        HomeFragment(),
        books = Author.sample.books
    )
}