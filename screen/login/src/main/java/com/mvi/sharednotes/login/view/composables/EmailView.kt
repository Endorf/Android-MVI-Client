package com.mvi.sharednotes.login.view.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mvi.sharednotes.login.R
import com.mvi.sharednotes.login.view.attributes.State
import com.mvi.sharednotes.ui.core.BaseOutlinedTextField
import com.mvi.sharednotes.ui.core.NO_ROUND_CORNER
import com.mvi.sharednotes.ui.core.ROUND_CORNER

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
        RoundedCornerShape(
            ROUND_CORNER.dp,
            ROUND_CORNER.dp,
            NO_ROUND_CORNER.dp,
            NO_ROUND_CORNER.dp
        ),
        KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        textChangeListener,
        onDoneClickListener,
        modifier = modifier
    )
}