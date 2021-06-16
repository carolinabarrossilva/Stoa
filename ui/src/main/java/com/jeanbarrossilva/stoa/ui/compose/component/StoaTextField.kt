package com.jeanbarrossilva.stoa.ui.compose.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.stoa.extensions.compose.modifier.attachFocusToKeyboardVisibility
import com.jeanbarrossilva.stoa.ui.compose.defaults.StoaTextFieldDefaults
import com.jeanbarrossilva.stoa.ui.compose.theme.StoaTheme

@Composable
fun StoaTextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isDisplayingError: Boolean = false,
    isSingleLine: Boolean = false,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = StoaTextFieldDefaults.colors
) {
    StoaTheme {
        TextField(
            value,
            onValueChange,
            modifier
                .attachFocusToKeyboardVisibility(),
            label = label,
            leadingIcon = leadingIcon,
            isError = isDisplayingError,
            keyboardActions = keyboardActions,
            singleLine = isSingleLine,
            shape = shape,
            colors = colors
        )
    }
}