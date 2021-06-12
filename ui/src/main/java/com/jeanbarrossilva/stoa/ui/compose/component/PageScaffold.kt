package com.jeanbarrossilva.stoa.ui.compose.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeanbarrossilva.stoa.extensions.compose.fontfamily.fontFamilyResource
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.compose.defaults.PageScaffoldDefaults.DefaultHorizontalPadding
import com.jeanbarrossilva.stoa.ui.compose.defaults.PageScaffoldDefaults.DefaultSpacing
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@Composable
internal fun PageScaffold(
    title: String,
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    headerModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    spacing: Dp = DefaultSpacing,
    contentHorizontalPadding: Dp = DefaultHorizontalPadding,
    content: @Composable () -> Unit
) {
    StoaTheme {
        Column(
            modifier
                .fillMaxSize()
                .padding(top = 135.dp),
            Arrangement.spacedBy(spacing)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                Text(
                    title,
                    titleModifier
                        .padding(horizontal = DefaultHorizontalPadding),
                    fontSize = 45.sp,
                    fontFamily = fontFamilyResource(R.font.android_euclid)
                )

                Surface(
                    headerModifier,
                    content = header
                )
            }

            Surface(
                contentModifier
                    .fillMaxWidth()
                    .padding(horizontal = contentHorizontalPadding),
                content = content
            )
        }
    }
}