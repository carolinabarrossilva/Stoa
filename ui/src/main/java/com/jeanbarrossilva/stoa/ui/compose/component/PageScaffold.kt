package com.jeanbarrossilva.stoa.ui.compose.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeanbarrossilva.stoa.extensions.compose.fontfamily.fontFamilyResource
import com.jeanbarrossilva.stoa.ui.R
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@Composable
internal fun PageScaffold(
    title: String,
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    StoaTheme {
        Column(
            modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
                .padding(top = 135.dp),
            Arrangement.spacedBy(60.dp)
        ) {
            Column(
                Modifier
                    .padding(horizontal = 25.dp),
                Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    title,
                    fontSize = 45.sp,
                    fontFamily = fontFamilyResource(R.font.android_euclid)
                )

                Surface(
                    Modifier
                        .fillMaxWidth(),
                    content = header
                )
            }

            Surface(
                Modifier
                    .fillMaxWidth(),
                content = content
            )
        }
    }
}