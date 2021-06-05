package com.jeanbarrossilva.stoa.extensions.compose.colors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.jeanbarrossilva.stoa.extensions.context.colorOf

private val defaultPrimaryColor = @Composable {
    if (isSystemInDarkTheme()) Color(0xFFBB86FC) else Color(0xFF6200EE)
}
private val defaultPrimaryVariantColor = @Composable {
    if (isSystemInDarkTheme()) Color(0xFF3700B3) else Color(0xFF3700B3)
}
private val defaultSecondaryColor = @Composable {
    if (isSystemInDarkTheme()) Color(0xFF03DAC6) else Color(0xFF03DAC6)
}
private val defaultSecondaryVariant = @Composable {
    if (isSystemInDarkTheme()) defaultSecondaryColor() else Color(0xFF018786)
}
private val defaultBackgroundColor = @Composable {
    if (isSystemInDarkTheme()) Color(0xFF121212) else Color.White
}
private val defaultSurfaceColor = @Composable {
    defaultBackgroundColor()
}
private val defaultErrorColor = @Composable {
    if (isSystemInDarkTheme()) Color(0xFFCF6679) else Color(0xFFB00020)
}
private val defaultOnPrimaryColor = @Composable {
    if (isSystemInDarkTheme()) Color.Black else Color.White
}
private val defaultOnSecondaryColor = @Composable {
    Color.Black
}
private val defaultOnBackgroundColor = @Composable {
    if (isSystemInDarkTheme()) Color.White else Color.Black
}
private val defaultOnSurfaceColor = @Composable {
    defaultOnBackgroundColor()
}
private val defaultOnErrorColor = @Composable {
    defaultOnPrimaryColor()
}

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
    primary: Color = defaultPrimaryColor(),
    primaryVariant: Color = defaultPrimaryVariantColor(),
    secondary: Color = defaultSecondaryColor(),
    secondaryVariant: Color = defaultSecondaryVariant(),
    background: Color = defaultBackgroundColor(),
    surface: Color = defaultSurfaceColor(),
    error: Color = defaultErrorColor(),
    onPrimary: Color = defaultOnPrimaryColor(),
    onSecondary: Color = defaultOnSecondaryColor(),
    onBackground: Color = defaultOnBackgroundColor(),
    onSurface: Color = defaultOnSurfaceColor(),
    onError: Color = defaultOnErrorColor()
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