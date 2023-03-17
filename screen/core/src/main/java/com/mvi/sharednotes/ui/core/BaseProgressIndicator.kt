package com.mvi.sharednotes.ui.core

import androidx.compose.animation.*
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
            initialOffsetY = { fullHeight -> -fullHeight },
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight },
        ) + fadeOut()
    ) {
        LinearProgressIndicator()
    }
}