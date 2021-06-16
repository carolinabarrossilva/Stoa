package com.jeanbarrossilva.stoa.extensions.compose.textfieldcolors

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val TextFieldColors.focusedIndicatorColor
    get() = colorIn("focusedIndicatorColor")
val TextFieldColors.focusedLabelColor
    get() = colorIn("focusedLabelColor")
val TextFieldColors.unfocusedIndicatorColor
    get() = colorIn("unfocusedIndicatorColor")
val TextFieldColors.unfocusedLabelColor: Color
    get() = colorIn("unfocusedLabelColor")

private fun TextFieldColors.colorIn(javaFieldName: String): Color {
    return if (this::class.simpleName == "DefaultTextFieldColors") {
        val value = this::class.java.getDeclaredField(javaFieldName).apply {
            isAccessible = true
        }.get(this) as Long
        val androidColor = android.graphics.Color.valueOf(value)

        Color(androidColor.toArgb())
    } else {
        Log.d("TextFieldColors", "colorIn: Could not find a color named \"$javaFieldName\" in ${this::class.qualifiedName}.")
        Color.Unspecified
    }
}

@Composable
fun TextFieldColors.copy(
    textColor: Color = textColor(enabled = true).value,
    disabledTextColor: Color = textColor(enabled = false).value,
    backgroundColor: Color = backgroundColor(enabled = true).value,
    cursorColor: Color = cursorColor(isError = false).value,
    errorCursorColor: Color = cursorColor(isError = true).value,
    focusedIndicatorColor: Color = this.focusedIndicatorColor,
    unfocusedIndicatorColor: Color = this.unfocusedIndicatorColor,
    disabledIndicatorColor: Color = indicatorColor(enabled = false, isError = false, MutableInteractionSource()).value,
    errorIndicatorColor: Color = indicatorColor(enabled = true, isError = true, MutableInteractionSource()).value,
    leadingIconColor: Color = leadingIconColor(enabled = true, isError = false).value,
    disabledLeadingIconColor: Color = leadingIconColor(enabled = false, isError = false).value,
    errorLeadingIconColor: Color = leadingIconColor(enabled = true, isError = true).value,
    trailingIconColor: Color = trailingIconColor(enabled = true, isError = false).value,
    disabledTrailingIconColor: Color = trailingIconColor(enabled = false, isError = false).value,
    errorTrailingIconColor: Color = trailingIconColor(enabled = true, isError = true).value,
    focusedLabelColor: Color = this.focusedLabelColor,
    unfocusedLabelColor: Color = this.unfocusedLabelColor,
    disabledLabelColor: Color = labelColor(enabled = false, error = false, MutableInteractionSource()).value,
    errorLabelColor: Color = labelColor(enabled = true, error = true, MutableInteractionSource()).value,
    placeholderColor: Color = placeholderColor(enabled = true).value,
    disabledPlaceholderColor: Color = placeholderColor(enabled = false).value
): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        textColor,
        disabledTextColor,
        backgroundColor,
        cursorColor,
        errorCursorColor,
        focusedIndicatorColor,
        unfocusedIndicatorColor,
        disabledIndicatorColor,
        errorIndicatorColor,
        leadingIconColor,
        disabledLeadingIconColor,
        errorLeadingIconColor,
        trailingIconColor,
        disabledTrailingIconColor,
        errorTrailingIconColor,
        focusedLabelColor,
        unfocusedLabelColor,
        disabledLabelColor,
        errorLabelColor,
        placeholderColor,
        disabledPlaceholderColor
    )
}