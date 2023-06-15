package com.mvi.sharednotes.login.view.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mvi.sharednotes.ui.core.BaseProgressIndicator

@Composable
fun ProgressIndicator(
    isLoading: Boolean,
    modifier: Modifier
) {
    BaseProgressIndicator(
        isLoading,
        modifier
    )
}