package com.mvi.sharednotes.login.view.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mvi.sharednotes.login.R
import com.mvi.sharednotes.login.view.attributes.State
import com.mvi.sharednotes.ui.core.BaseOutlinedTextField

@Composable
fun EmailView(
    state: State,
    textChangeListener: (String) -> Unit,
    onDoneClickListener: () -> Unit,
    modifier: Modifier
) {
    BaseOutlinedTextField(
        state.email,
        state.isLoading,
        state.hasError,
        state.errorMessage,
        stringResource(id = R.string.login_email_hint),
        stringResource(id = R.string.login_email_hint),
        Icons.Default.Email,
        textChangeListener,
        onDoneClickListener,
        modifier
    )
}