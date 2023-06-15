package com.mvi.sharednotes.login.view.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mvi.sharednotes.login.R
import com.mvi.sharednotes.ui.core.BaseButton

@Composable
fun LoginButton(
    isLoading: Boolean,
    onLoginClickListener: () -> Unit,
    modifier: Modifier
) {
    BaseButton(
        isLoading,
        stringResource(id = R.string.login_button_title),
        onLoginClickListener,
        modifier
    )
}