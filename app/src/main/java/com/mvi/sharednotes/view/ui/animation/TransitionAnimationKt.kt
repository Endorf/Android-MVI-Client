package com.mvi.sharednotes.view.ui.animation

import androidx.compose.animation.core.keyframes
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

private const val INITIAL_OFFSET_X = 1500
private const val ENTER_DURATION = 200
private const val EXIT_DURATION = 300
private const val POP_EXIT_DURATION = 300
private const val POP_ENTER_DURATION = 200

fun enterTransition() = slideInHorizontally(
    animationSpec = keyframes { durationMillis = ENTER_DURATION },
    initialOffsetX = { INITIAL_OFFSET_X }
)

fun exitTransition() = slideOutHorizontally(
    animationSpec = keyframes { durationMillis = EXIT_DURATION },
    targetOffsetX = { -INITIAL_OFFSET_X }
)

fun popEnterTransition() = slideInHorizontally(
    animationSpec = keyframes { durationMillis = POP_ENTER_DURATION },
    initialOffsetX = { -INITIAL_OFFSET_X }
)

fun popExitTransition() = slideOutHorizontally(
    animationSpec = keyframes { durationMillis = POP_EXIT_DURATION },
    targetOffsetX = { INITIAL_OFFSET_X }
)