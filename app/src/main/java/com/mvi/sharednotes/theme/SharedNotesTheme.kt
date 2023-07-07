package com.mvi.sharednotes.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SharedNotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    /* TODO: Update color scheme according to dynamicColorScheme
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (darkTheme) DarkColorScheme else LightColorScheme
    }
     */

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, darkTheme) {
        systemUiController.systemBarsDarkContentEnabled = !darkTheme
        systemUiController.setStatusBarColor(color = colorScheme.secondary)
        systemUiController.setNavigationBarColor(color = colorScheme.secondary)
        onDispose {}
    }

    val defaultGradientColors = Gradient(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface
    )

    val defaultBackgroundTheme = Background(
        color = colorScheme.surface,
        tonalElevation = 2.dp
    )

    CompositionLocalProvider(
        LocalGradientColors provides defaultGradientColors,
        LocalBackgroundTheme provides defaultBackgroundTheme,
        LocalTintTheme provides Tint()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = Shapes,
            typography = Typography,
            content = content
        )
    }
}

internal val DarkColorScheme = darkColorScheme(
    primary = ExperimentalColorPrimary,
    onPrimary = ExperimentalColorOnPrimary,
    secondary = ExperimentalColorSecondary,
    primaryContainer = ExperimentalColorPrimaryContainer,
    onPrimaryContainer = Blue90,
    inversePrimary = Blue40,
    onSecondary = DarkBlue20,
    secondaryContainer = ExperimentalColorSecondaryContainer,
    onSecondaryContainer = DarkBlue90,
    tertiary = ExperimentalTertiaryDark,
    onTertiary = Color.White,
    tertiaryContainer = Yellow30,
    onTertiaryContainer = Yellow90,
    error = ExperimentalColorError,
    onError = ExperimentalColorOnError,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Grey90,
    surface = Grey10,
    onSurface = ExperimentalColorOnSurfaceDark,
    inverseSurface = Grey90,
    inverseOnSurface = Grey20,
    surfaceVariant = BlueGrey30,
    onSurfaceVariant = BlueGrey80,
    outline = BlueGrey60
)

internal val LightColorScheme = lightColorScheme(
    primary = ExperimentalColorPrimary,
    onPrimary = ExperimentalColorOnPrimary,
    secondary = ExperimentalColorSecondary,
    primaryContainer = ExperimentalColorPrimaryContainer,
    onPrimaryContainer = Blue10,
    inversePrimary = Blue80,
    onSecondary = Color.White,
    secondaryContainer = ExperimentalColorSecondaryContainer,
    onSecondaryContainer = DarkBlue10,
    tertiary = ExperimentalTertiary,
    onTertiary = ExperimentalOnTertiary,
    tertiaryContainer = Yellow90,
    onTertiaryContainer = Yellow10,
    error = ExperimentalColorError,
    onError = ExperimentalColorOnError,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = Grey99,
    onSurface = ExperimentalColorOnSurfaceLight,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = BlueGrey90,
    onSurfaceVariant = BlueGrey30,
    outline = BlueGrey50
)