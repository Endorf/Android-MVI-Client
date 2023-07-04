package com.mvi.sharednotes.ui.core

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BaseButton(
    isLoading: Boolean,
    title: String,
    modifier: Modifier,
    shape: RoundedCornerShape,
    onClickListener: () -> Unit,
) {
    Button(
        modifier = modifier,
        enabled = !isLoading,
        onClick = onClickListener,
        shape = shape
    ) {
        Text(
            text = title,
            modifier = modifier
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}