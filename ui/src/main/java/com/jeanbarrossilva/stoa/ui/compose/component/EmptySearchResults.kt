package com.jeanbarrossilva.stoa.ui.compose.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@Composable
fun EmptySearchResults(modifier: Modifier = Modifier) {
    StoaTheme {
        Column(
            modifier,
            Arrangement.spacedBy(15.dp),
            Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Rounded.Search,
                contentDescription = null,
                Modifier
                    .size(30.dp)
                    .alpha(ContentAlpha.medium)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Search",
                    Modifier
                        .alpha(ContentAlpha.medium),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Your search results will appear here.",
                    Modifier
                        .alpha(ContentAlpha.medium)
                )
            }
        }
    }
}