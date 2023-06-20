package com.mvi.sharednotes.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

object TopAppBarColor {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun centerAlignedTopAppBarColors() = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.onPrimary
    )
}