package com.jeanbarrossilva.stoa.extensions.compose.colors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.jeanbarrossilva.stoa.extensions.context.colorOf

@Composable
fun androidColors(
    primary: Color = Color(LocalContext.current colorOf android.R.attr.colorPrimary),
    primaryVariant: Color = Color(LocalContext.current colorOf android.R.attr.colorPrimaryDark),
    secondary: Color = Color(LocalContext.current colorOf android.R.attr.colorSecondary),
    secondaryVariant: Color = Color(LocalContext.current colorOf android.R.attr.colorSecondary),
    background: Color = Color(LocalContext.current colorOf android.R.attr.colorBackground),
    surface: Color = Color(LocalContext.current colorOf com.google.android.material.R.attr.colorSurface),
    error:  Color = Color(LocalContext.current colorOf android.R.attr.colorError),
    onPrimary:  Color = Color(LocalContext.current colorOf com.google.android.material.R.attr.colorOnPrimary),
    onSecondary:  Color = Color(LocalContext.current colorOf com.google.android.material.R.attr.colorOnSecondary),
    onBackground: Color = Color(LocalContext.current colorOf com.google.android.material.R.attr.colorOnBackground),
    onSurface: Color = Color(LocalContext.current colorOf com.google.android.material.R.attr.colorOnSurface),
    onError: Color = Color(LocalContext.current colorOf com.google.android.material.R.attr.colorOnError)
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
    primary: Color = if (isSystemInDarkTheme()) Color(0xFFBB86FC) else Color(0xFF6200EE),
    primaryVariant: Color = if (isSystemInDarkTheme()) Color(0xFF3700B3) else Color(0xFF3700B3),
    secondary: Color = if (isSystemInDarkTheme()) Color(0xFF03DAC6) else Color(0xFF03DAC6),
    secondaryVariant: Color = if (isSystemInDarkTheme()) secondary else Color(0xFF018786),
    background: Color = if (isSystemInDarkTheme()) Color(0xFF121212) else Color.White,
    surface: Color = background,
    error: Color = if (isSystemInDarkTheme()) Color(0xFFCF6679) else Color(0xFFB00020),
    onPrimary: Color = if (isSystemInDarkTheme()) Color.Black else Color.White,
    onSecondary: Color = Color.Black,
    onBackground: Color = if (isSystemInDarkTheme()) Color.White else Color.Black,
    onSurface: Color = onBackground,
    onError: Color = onPrimary
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