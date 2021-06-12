package com.jeanbarrossilva.stoa.ui.compose.defaults

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.stoa.extensions.compose.any.doIf
import com.jeanbarrossilva.stoa.extensions.compose.modifier.attachFocusToKeyboardVisibility
import com.jeanbarrossilva.stoa.extensions.compose.modifier.withPlaceable

object SearchPageScaffoldDefaults {
    private var searchFieldHeight by mutableStateOf(0)
    private var searchFieldY by mutableStateOf(0)

    @Composable
    private fun getDistanceToScreenTopFrom(y: Int): Int {
        return LocalContext.current.resources.displayMetrics.heightPixels.let { screenHeight ->
            screenHeight - (screenHeight - y)
        }
    }

    fun Modifier.title(isSearching: Boolean): Modifier {
        return composed {
            val titleOffsetY by animateDpAsState(if (isSearching) (-70).dp else 0.dp, tween(durationMillis = 100))
            val titleAlpha by animateFloatAsState(if (isSearching) 0f else 1f)

            offset(y = titleOffsetY)
                .alpha(titleAlpha)
        }
    }

    fun Modifier.header(isSearching: Boolean): Modifier {
        return composed {
            var defaultY by remember {
                mutableStateOf(0)
            }
            val y by animateIntAsState(if (isSearching) -getDistanceToScreenTopFrom(defaultY) else 0, tween())
            val horizontalPadding by animateDpAsState(if (isSearching) 0.dp else PageScaffoldDefaults.DefaultHorizontalPadding)

            onGloballyPositioned { coordinates ->
                defaultY = coordinates.positionInRoot().y.toInt()
            }
                .withPlaceable { scope ->
                    scope.layout(width, height) {
                        place(x = 0, y)
                    }
                }
                .padding(horizontal = horizontalPadding)
        }
    }

    fun Modifier.content(isSearching: Boolean): Modifier {
        return composed {
            var defaultY by remember {
                mutableStateOf(0)
            }
            val y by animateIntAsState(
                if (isSearching) -getDistanceToScreenTopFrom(defaultY) + (searchFieldY + searchFieldHeight) else 0,
                tween()
            )

            onGloballyPositioned { coordinates ->
                defaultY = coordinates.positionInRoot().y.toInt()
            }
                .withPlaceable { scope ->
                    scope.layout(width, height) {
                        place(x = 0, y)
                    }
                }
        }
    }

    fun Modifier.searchField(
        isSearching: Boolean,
        focusRequester: FocusRequester = FocusRequester(),
        onFocusChanged: (isFocused: Boolean) -> Unit
    ): Modifier {
        return composed {
            val screenWidth = LocalContext.current.resources.configuration.screenWidthDp.dp
            val width by animateDpAsState(if (isSearching) screenWidth else TextFieldDefaults.MinWidth)
            val height by animateDpAsState(TextFieldDefaults.MinHeight.doIf(isSearching) {
                plus(15.dp)
            })

            onGloballyPositioned { coordinates ->
                searchFieldHeight = coordinates.size.height
                searchFieldY = coordinates.positionInRoot().y.toInt()
            }
                .fillMaxWidth()
                .height(height)
                .width(width)
                .attachFocusToKeyboardVisibility(focusRequester, onFocusChanged)
        }
    }

    @Composable
    fun searchFieldShape(isSearching: Boolean): Shape {
        val size by animateDpAsState(if (isSearching) 0.dp else 10.dp)
        return RoundedCornerShape(size)
    }
}