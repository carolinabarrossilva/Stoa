package com.jeanbarrossilva.stoa.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.stoa.extensions.any.doIf
import com.jeanbarrossilva.stoa.extensions.compose.composition.LocalActivity
import com.jeanbarrossilva.stoa.extensions.compose.lazylistscope.items
import com.jeanbarrossilva.stoa.model.Author
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.adapter.BookAdapter
import com.jeanbarrossilva.stoa.ui.compose.component.BookSearchItem
import com.jeanbarrossilva.stoa.ui.compose.component.EmptySearchResults
import com.jeanbarrossilva.stoa.ui.compose.component.SearchPageScaffold
import com.jeanbarrossilva.stoa.ui.compose.component.Section
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme
import com.jeanbarrossilva.stoa.ui.fragment.HomeFragment

@Composable
fun HomeUI(fragment: HomeFragment, books: List<Book>, modifier: Modifier = Modifier) {
    var searchQuery by remember {
        mutableStateOf("")
    }
    val matchingBooks = fragment.books.filter { book ->
        searchQuery in book
    }.doIf(searchQuery.isEmpty()) {
        emptyList()
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
                    if (searchQuery.isEmpty())
                        EmptySearchResults(
                            Modifier
                                .padding(top = 50.dp)
                                .padding(horizontal = 20.dp)
                        )
                    else
                        LazyColumn {
                            items(matchingBooks, topSpacing = 20.dp, bottomSpacing = 20.dp) { book ->
                                AndroidView({ context ->
                                    ComposeView(context)
                                }) { view ->
                                    view.setContent {
                                        BookSearchItem(
                                            book,
                                            view::performClick,
                                            Modifier
                                                .padding(horizontal = 20.dp)
                                        )
                                    }
                                    view.setOnClickListener {
                                        fragment.onBookClick(view, book)
                                    }
                                }
                            }
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
                        view.adapter = BookAdapter(books, fragment::onBookClick)
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