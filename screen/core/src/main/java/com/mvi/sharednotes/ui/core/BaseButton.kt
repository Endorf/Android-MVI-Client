package com.mvi.sharednotes.ui.core

import androidx.compose.foundation.layout.fillMaxSize
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
    onClickListener: () -> Unit,
    modifier: Modifier
) {
    Button(
        modifier = modifier,
        enabled = !isLoading,
        onClick = { onClickListener() },
        shape = RoundedCornerShape(5.dp),
    ) {
        Text(
            text = title,
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}