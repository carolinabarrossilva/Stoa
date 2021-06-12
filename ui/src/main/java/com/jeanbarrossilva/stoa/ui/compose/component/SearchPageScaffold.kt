package com.jeanbarrossilva.stoa.ui.compose.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.stoa.extensions.compose.color.colorOf
import com.jeanbarrossilva.stoa.extensions.compose.view.onKeyboardEvent
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.compose.defaults.PageScaffoldDefaults
import com.jeanbarrossilva.stoa.ui.compose.defaults.SearchPageScaffoldDefaults
import com.jeanbarrossilva.stoa.ui.compose.defaults.SearchPageScaffoldDefaults.content
import com.jeanbarrossilva.stoa.ui.compose.defaults.SearchPageScaffoldDefaults.header
import com.jeanbarrossilva.stoa.ui.compose.defaults.SearchPageScaffoldDefaults.searchField
import com.jeanbarrossilva.stoa.ui.compose.defaults.SearchPageScaffoldDefaults.title
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchPageScaffold(
    title: String,
    query: String,
    onQueryChange: (query: String) -> Unit,
    modifier: Modifier = Modifier,
    searchContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val searchFieldFocusRequester = FocusRequester()
    var isSearching by remember {
        mutableStateOf(false)
    }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val pageScaffoldSpacing by animateDpAsState(targetValue = if (isSearching) 20.dp else PageScaffoldDefaults.DefaultSpacing)

    run {
        onKeyboardEvent { isOpen ->
            if (isSearching && !isOpen) {
                isSearching = false
                focusManager.clearFocus()
            }
        }
    }

    StoaTheme {
        PageScaffold(
            title,
            modifier
                .fillMaxSize(),
            Modifier
                .title(isSearching),
            Modifier
                .header(isSearching),
            Modifier
                .content(isSearching),
            header = {
                TextField(
                    query,
                    onQueryChange,
                    Modifier
                        .searchField(isSearching, searchFieldFocusRequester) { isFocused ->
                            isSearching = isFocused
                        },
                    label = {
                        Text(stringResource(R.string.SearchPageScaffold_field_hint))
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.Search,
                            contentDescription = null,
                            Modifier
                                .size(20.dp)
                        )
                    },
                    keyboardActions = KeyboardActions(onDone = {
                        searchFieldFocusRequester.freeFocus()
                        softwareKeyboardController?.hide()
                    }),
                    singleLine = true,
                    shape = SearchPageScaffoldDefaults.searchFieldShape(isSearching),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = if (isSystemInDarkTheme()) colorOf("#303030") else colorOf("#F9F9F9"),
                        cursorColor = MaterialTheme.colors.onSurface,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Transparent
                    )
                )
            },
            spacing = pageScaffoldSpacing,
            content = if (isSearching) searchContent else content
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SearchPageScaffold_Preview() {
    SearchPageScaffold(
        title = "Home",
        query = "",
        onQueryChange = {
        },
        searchContent = {
        }
    ) {
    }
}