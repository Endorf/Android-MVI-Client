package com.mvi.sharednotes.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class Gradient(
    val top: Color = Color.Unspecified,
    val bottom: Color = Color.Unspecified,
    val container: Color = Color.Unspecified
)

val LocalGradientColors = staticCompositionLocalOf { Gradient() }