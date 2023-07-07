package com.mvi.sharednotes.creation.view.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mvi.sharednotes.creation.R
import com.mvi.sharednotes.creation.view.attributes.State
import com.mvi.sharednotes.ui.core.BaseOutlinedTextField
import com.mvi.sharednotes.ui.core.NO_ROUND_CORNER

@Composable
fun TitleField(state: State, modifier: Modifier, titleTextChangeListener: (String) -> Unit) {
    val focusManager = LocalFocusManager.current
    BaseOutlinedTextField(
        state.title,
        state.isLoading,
        state.isTitleError,
        state.errorMessage,
        stringResource(id = R.string.title_label),
        stringResource(id = R.string.title_placeholder),
        null,
        RoundedCornerShape(NO_ROUND_CORNER.dp),
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        titleTextChangeListener,
        onNextClickListener = { focusManager.moveFocus(FocusDirection.Down) },
        modifier = modifier
    )
}