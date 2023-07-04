package com.mvi.sharednotes.ui.core

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

private const val FAB_CORNER_SHAPE = 4

@Composable
fun BaseFloatingActionButton(
    modifier: Modifier,
    contentDescription: String,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.secondary,
        modifier = modifier,
        shape = RoundedCornerShape(FAB_CORNER_SHAPE.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSecondary,
        )
    }
}