package com.jeanbarrossilva.stoa.ui.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.stoa.extensions.compose.color.colorOf
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@Composable
fun SearchScaffold(
    title: String,
    query: String,
    onQueryChange: (query: String) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val fieldBackgroundColor = if (isSystemInDarkTheme()) colorOf("#F9F9F9") else MaterialTheme.colors.surface
    var _query by remember {
        mutableStateOf(query)
    }

    StoaTheme {
        PageScaffold(
            title,
            header = {
                BasicTextField(_query, onValueChange = {
                    _query = it
                    onQueryChange(it)
                }) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(fieldBackgroundColor, RoundedCornerShape(10.dp))
                            .padding(15.dp),
                        Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Search,
                            contentDescription = "Search",
                            Modifier
                                .size(20.dp)
                                .alpha(ContentAlpha.medium)
                        )

                        if (_query.isEmpty())
                            Text(
                                "Search...",
                                Modifier
                                    .alpha(ContentAlpha.medium)
                            )
                        else
                            Text(_query)
                    }
                }
            },
            content = content
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SearchScaffold_Preview() {
    SearchScaffold(
        title = "Home",
        query = "",
        onQueryChange = {
        }
    ) {
    }
}