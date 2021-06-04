package com.jeanbarrossilva.stoa.ui.view.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.jeanbarrossilva.stoa.ui.R

@Composable
fun Session(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    actionButtonTitle: String = stringResource(R.string.SessionLayout_actionButtonTitle),
    onActionButtonClick: () -> Unit,
    spacing: Dp = 25.dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier
            .fillMaxWidth(),
        Arrangement.spacedBy(0.dp, Alignment.CenterVertically)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .padding(bottom = spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.5f),
                Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    title,
                    fontSize = 20.sp,
                    fontFamily = Font(R.font.android_euclid).toFontFamily()
                )

                Text(
                    description,
                    Modifier.alpha(ContentAlpha.medium),
                    fontFamily = Font(R.font.android_euclid_medium).toFontFamily()
                )
            }

            Box(
                Modifier
                    .fillMaxWidth(),
                Alignment.CenterEnd
            ) {
                AndroidView({ context ->
                    ComposeView(context)
                }) { view ->
                    view.setContent {
                        Row(
                            Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .clickable(onClick = onActionButtonClick)
                                .padding(2.dp),
                            Arrangement.spacedBy(5.dp),
                            Alignment.CenterVertically
                        ) {
                            Text(
                                actionButtonTitle,
                                color = MaterialTheme.colors.primary,
                                fontFamily = Font(R.font.android_euclid_medium).toFontFamily(),
                                textAlign = TextAlign.End
                            )

                            Icon(
                                Icons.Rounded.ArrowForwardIos,
                                contentDescription = null,
                                Modifier
                                    .size(12.dp),
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }
        }

        content()
    }
}

@Preview(showBackground = true)
@Composable
fun Session_Preview() {
    Session(
        title = stringResource(R.string.SessionLayout_popular_title),
        description = stringResource(R.string.SessionLayout_popular_description),
        onActionButtonClick = {
        }
    ) {
    }
}