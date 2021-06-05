package com.jeanbarrossilva.stoa.ui.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    actionButtonTitle: String = stringResource(R.string.SessionLayout_actionButtonTitle),
    onActionButtonClick: () -> Unit,
    spacing: Dp = 25.dp,
    padding: Dp = spacing,
    content: @Composable () -> Unit
) {
    StoaTheme {
        Column(
            modifier
                .fillMaxWidth(),
            Arrangement.spacedBy(spacing)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .padding(top = 25.dp),
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
                        Modifier.alpha(ContentAlpha.medium)
                    )
                }

                Box(
                    Modifier
                        .fillMaxWidth(),
                    Alignment.CenterEnd
                ) {
                    Button(
                        onClick = onActionButtonClick,
                        Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .clickable(onClick = onActionButtonClick),
                        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = MaterialTheme.colors.primary
                        ),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                actionButtonTitle,
                                color = MaterialTheme.colors.primary,
                                letterSpacing = 0.sp,
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

            Surface(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = padding),
                content = content
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Section_Preview() {
    Section(
        title = stringResource(R.string.SessionLayout_popular_title),
        description = stringResource(R.string.SessionLayout_popular_description),
        onActionButtonClick = {
        }
    ) {
    }
}