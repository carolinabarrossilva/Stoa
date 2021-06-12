package com.jeanbarrossilva.stoa.ui.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.glide.rememberGlidePainter
import com.jeanbarrossilva.stoa.model.Book
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@Composable
fun BookSearchItem(book: Book, onClick: () -> Unit, modifier: Modifier = Modifier) {
    StoaTheme {
        Row(
            modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable(onClick = onClick)
                .padding(15.dp),
            Arrangement.spacedBy(20.dp),
            Alignment.CenterVertically
        ) {
            Image(
                rememberGlidePainter(book.cover.url),
                contentDescription = stringResource(R.string.view_book_content_description_cover).format(book.author.name, book.title),
                Modifier
                    .width(40.dp)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(5.dp))
            )

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    book.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                Text(
                    book.author.name,
                    Modifier
                        .alpha(ContentAlpha.medium),
                    fontSize = 14.sp,
                )
            }
        }
    }
}