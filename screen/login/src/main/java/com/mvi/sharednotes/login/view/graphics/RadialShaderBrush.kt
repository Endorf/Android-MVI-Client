@file:Suppress("MagicNumber")

package com.mvi.sharednotes.login.view.graphics

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

object RadialShaderBrush : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xffffd89b), Color(0xffdc2430)),
            center = size.center,
            radius = biggerDimension / 1.7f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}