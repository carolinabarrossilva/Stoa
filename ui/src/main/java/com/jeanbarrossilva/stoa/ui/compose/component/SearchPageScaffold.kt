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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.stoa.extensions.compose.color.colorOf
import com.jeanbarrossilva.stoa.extensions.compose.modifier.doIf
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@Suppress("LocalVariableName")
@Composable
fun SearchPageScaffold(
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
    val fieldModifier = Modifier.doIf(query.isEmpty()) {
        alpha(ContentAlpha.medium)
    }
    val fieldText = if (_query.isEmpty()) "Search..." else _query

    StoaTheme {
        PageScaffold(
            title,
            modifier,
            header = {
                BasicTextField(
                    _query,
                    onValueChange = {
                        _query = it
                        onQueryChange(it)
                    },
                    Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .shadow(8.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(fieldBackgroundColor)
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

                        Text(
                            fieldText,
                            fieldModifier
                        )
                    }
                }
            },
            content = content
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
        }
    ) {
    }
}