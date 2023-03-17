package com.mvi.sharednotes.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SharedNotesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (darkTheme) DarkColorPalette else LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, darkTheme) {
        systemUiController.systemBarsDarkContentEnabled = !darkTheme

        onDispose {}
    }

    val defaultGradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface,
    )

    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp,
    )

    CompositionLocalProvider(
        LocalGradientColors provides defaultGradientColors,
        LocalBackgroundTheme provides defaultBackgroundTheme,
        LocalTintTheme provides TintTheme(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = Shapes,
            typography = Typography,
            content = content,
        )
    }
}

private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200
)

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    secondary = Teal200
)
