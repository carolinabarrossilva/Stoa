package com.jeanbarrossilva.stoa.extensions.compose.colors

import androidx.annotation.AttrRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.jeanbarrossilva.stoa.extensions.compose.context.colorOf
import com.jeanbarrossilva.stoa.extensions.compose.view.isInPreview

private val defaultPrimaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFBB86FC) else Color(0xFF6200EE)
private val defaultPrimaryVariantColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF3700B3) else Color(0xFF3700B3)
private val defaultSecondaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF03DAC6) else Color(0xFF03DAC6)
private val defaultSecondaryVariantColor
    @Composable
    get() = if (isSystemInDarkTheme()) defaultSecondaryColor else Color(0xFF018786)
private val defaultBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF121212) else Color.White
private val defaultSurfaceColor
    @Composable
    get() = defaultBackgroundColor
private val defaultErrorColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFCF6679) else Color(0xFFB00020)
private val defaultOnPrimaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Color.White
private val defaultOnSecondaryColor
    @Composable
    get() = Color.Black
private val defaultOnBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black
private val defaultOnSurfaceColor
    @Composable
    get() = defaultOnBackgroundColor
private val defaultOnErrorColor
    @Composable
    get() = defaultOnPrimaryColor

private val previewablePrimaryColor
    @Composable
    get() = if (isInPreview()) defaultPrimaryColor else colorOf(android.R.attr.colorPrimary)
private val previewablePrimaryVariantColor
    @Composable
    get() = if (isInPreview()) defaultPrimaryVariantColor else colorOf(android.R.attr.colorPrimaryDark)
private val previewableSecondaryColor
    @Composable
    get() = if (isInPreview()) defaultSecondaryColor else colorOf(android.R.attr.colorSecondary)
private val previewableSecondaryVariantColor
    @Composable
    get() = if (isInPreview()) defaultSecondaryVariantColor else colorOf(com.google.android.material.R.attr.colorSecondaryVariant)
private val previewableBackgroundColor
    @Composable
    get() = if (isInPreview()) defaultBackgroundColor else colorOf(android.R.attr.colorBackground)
private val previewableSurfaceColor
    @Composable
    get() = if (isInPreview()) defaultSurfaceColor else colorOf(com.google.android.material.R.attr.colorSurface)
private val previewableErrorColor
    @Composable
    get() = if (isInPreview()) defaultErrorColor else colorOf(android.R.attr.colorError)
private val previewableOnPrimaryColor
    @Composable
    get() = if (isInPreview()) defaultOnPrimaryColor else colorOf(com.google.android.material.R.attr.colorOnPrimary)
private val previewableOnSecondaryColor
    @Composable
    get() = if (isInPreview()) defaultOnSecondaryColor else colorOf(com.google.android.material.R.attr.colorOnSecondary)
private val previewableOnBackgroundColor
    @Composable
    get() = if (isInPreview()) defaultOnBackgroundColor else colorOf(com.google.android.material.R.attr.colorOnBackground)
private val previewableOnSurfaceColor
    @Composable
    get() = if (isInPreview()) defaultOnSurfaceColor else colorOf(com.google.android.material.R.attr.colorOnSurface)
private val previewableOnErrorColor
    @Composable
    get() = if (isInPreview()) defaultOnErrorColor else colorOf(com.google.android.material.R.attr.colorOnError)

@Composable
private fun colorOf(@AttrRes attrRes: Int): Color {
    return LocalContext.current colorOf attrRes
}

@Composable
fun androidColors(
    primary: Color = previewablePrimaryColor,
    primaryVariant: Color = previewablePrimaryVariantColor,
    secondary: Color = previewableSecondaryColor,
    secondaryVariant: Color = previewableSecondaryVariantColor,
    background: Color = previewableBackgroundColor,
    surface: Color = previewableSurfaceColor,
    error:  Color = previewableErrorColor,
    onPrimary:  Color = previewableOnPrimaryColor,
    onSecondary:  Color = previewableOnSecondaryColor,
    onBackground: Color = previewableOnBackgroundColor,
    onSurface: Color = previewableOnSurfaceColor,
    onError: Color = previewableOnErrorColor
): Colors {
    return colors(
        primary,
        primaryVariant,
        secondary,
        secondaryVariant,
        background,
        surface,
        error,
        onPrimary,
        onSecondary,
        onBackground,
        onSurface,
        onError
    )
}

@Composable
fun colors(
    primary: Color = defaultPrimaryColor,
    primaryVariant: Color = defaultPrimaryVariantColor,
    secondary: Color = defaultSecondaryColor,
    secondaryVariant: Color = defaultSecondaryVariantColor,
    background: Color = defaultBackgroundColor,
    surface: Color = defaultSurfaceColor,
    error: Color = defaultErrorColor,
    onPrimary: Color = defaultOnPrimaryColor,
    onSecondary: Color = defaultOnSecondaryColor,
    onBackground: Color = defaultOnBackgroundColor,
    onSurface: Color = defaultOnSurfaceColor,
    onError: Color = defaultOnErrorColor
): Colors {
    return if (isSystemInDarkTheme()) {
        darkColors(
            primary,
            primaryVariant,
            secondary,
            secondaryVariant,
            background,
            surface,
            error,
            onPrimary,
            onSecondary,
            onBackground,
            onSurface,
            onError
        )
    } else {
        lightColors(
            primary,
            primaryVariant,
            secondary,
            secondaryVariant,
            background,
            surface,
            error,
            onPrimary,
            onSecondary,
            onBackground,
            onSurface,
            onError
        )
    }
}