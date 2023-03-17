package com.mvi.sharednotes.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicLightColorScheme
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

private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200
)

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    secondary = Teal200
)
