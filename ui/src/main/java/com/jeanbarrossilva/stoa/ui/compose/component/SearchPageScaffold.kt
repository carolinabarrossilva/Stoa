package com.jeanbarrossilva.stoa.ui.compose.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.stoa.extensions.compose.color.colorOf
import com.jeanbarrossilva.stoa.ui.R
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
    StoaTheme {
        PageScaffold(
            title,
            modifier,
            header = {
                TextField(
                    query,
                    onQueryChange,
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
                    shape = RoundedCornerShape(10.dp),
                    colors = textFieldColors(
                        backgroundColor = colorOf("#F9F9F9"),
                        cursorColor = MaterialTheme.colors.onSurface,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                    )
                )
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