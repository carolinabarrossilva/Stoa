package com.jeanbarrossilva.stoa.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.stoa.extensions.context.activity.fab
import com.jeanbarrossilva.stoa.ui.compose.component.StoaTextField
import com.jeanbarrossilva.stoa.ui.compose.defaults.BookComposerDefaults.bookCover
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme
import com.jeanbarrossilva.stoa.ui.fragment.BookComposerFragment
import com.jeanbarrossilva.stoa.ui.viewmodel.BookComposerViewModel

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun BookComposerUI(
    fragment: BookComposerFragment,
    viewModel: BookComposerViewModel,
    modifier: Modifier = Modifier
) {
    val coverBitmap by viewModel.getCoverBitmap().observeAsState()
    val title by viewModel.getTitle().observeAsState("")
    val subtitle by viewModel.getSubtitle().observeAsState("")
    val description by viewModel.getDescription().observeAsState("")

    val displaysBlankTitleError = remember {
        mutableStateOf(false)
    }
    val displaysBlankSubtitleError = remember {
        mutableStateOf(false)
    }
    val displaysBlankDescriptionError = remember {
        mutableStateOf(false)
    }

    run {
        fragment.activity?.fab?.setOnClickListener {
            listOf(
                title to displaysBlankTitleError,
                subtitle to displaysBlankSubtitleError,
                description to displaysBlankDescriptionError
            ).onEach { (value, displaysBlankError) ->
                displaysBlankError.value = value.isBlank()
            }.all { (_, isDisplayingError) ->
                !isDisplayingError.value
            }.let { isNotDisplayingErrors ->
                if (isNotDisplayingErrors) {
                    fragment.compose()
                    fragment.findNavController().popBackStack()
                }
            }
        }
    }

    StoaTheme(modifier) {
        LazyColumn(contentPadding = PaddingValues(bottom = 88.dp)) {
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                ) {
                    Surface(
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        coverBitmap?.let { coverBitmap ->
                            Image(
                                BitmapPainter(coverBitmap.asImageBitmap()),
                                contentDescription = null,
                                Modifier
                                    .bookCover()
                                    .clickable(onClick = fragment::showAvailableImages),
                                contentScale = ContentScale.Crop
                            )
                        } ?: Card(
                            onClick = fragment::showAvailableImages,
                            Modifier
                                .bookCover()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    Icons.Rounded.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.primary
                                )

                                Text(
                                    "Add cover".uppercase(),
                                    color = MaterialTheme.colors.primary,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    Column(
                        Modifier
                            .fillMaxWidth(),
                        Arrangement.spacedBy(25.dp)
                    ) {
                        Column(
                            Modifier
                                .fillMaxWidth(),
                            Arrangement.spacedBy(10.dp)
                        ) {
                            StoaTextField(
                                title,
                                onValueChange = viewModel::setTitle,
                                Modifier
                                    .fillMaxWidth(),
                                label = {
                                    Text("Title")
                                },
                                isDisplayingError = displaysBlankTitleError.value,
                                isSingleLine = true
                            )

                            StoaTextField(
                                subtitle,
                                onValueChange = viewModel::setSubtitle,
                                Modifier
                                    .fillMaxWidth(),
                                label = {
                                    Text("Subtitle")
                                },
                                isDisplayingError = displaysBlankSubtitleError.value,
                                isSingleLine = true
                            )
                        }

                        StoaTextField(
                            description,
                            onValueChange = viewModel::setDescription,
                            Modifier
                                .fillMaxWidth(),
                            label = {
                                Text("Description")
                            },
                            isDisplayingError = displaysBlankDescriptionError.value
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BookComposerUI_Preview() {
    BookComposerUI(
        BookComposerFragment(),
        BookComposerViewModel()
    )
}