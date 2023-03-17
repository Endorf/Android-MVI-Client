package com.mvi.sharednotes.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class Tint(
    val iconTint: Color? = null
)

val LocalTintTheme = staticCompositionLocalOf { Tint() }