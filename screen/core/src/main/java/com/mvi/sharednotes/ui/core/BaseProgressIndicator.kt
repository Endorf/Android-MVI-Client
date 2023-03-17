package com.mvi.sharednotes.ui.core

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.fadeOut
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProgressView(
    isLoading: Boolean,
    modifier: Modifier
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isLoading,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight }
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight }
        ) + fadeOut()
    ) {
        LinearProgressIndicator()
    }
}