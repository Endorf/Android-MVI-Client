package com.mvi.sharednotes.creation.view.composables

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
import com.mvi.sharednotes.creation.R
import com.mvi.sharednotes.creation.view.attributes.State
import com.mvi.sharednotes.ui.core.BaseOutlinedTextField
import com.mvi.sharednotes.ui.core.NO_ROUND_CORNER

@Composable
fun DescriptionField(
    state: State,
    modifier: Modifier,
    descriptionTextChangeListener: (String) -> Unit,
    onDoneClickListener: () -> Unit
) {
    BaseOutlinedTextField(
        state.description,
        state.isLoading,
        state.hasError,
        state.errorMessage,
        stringResource(id = R.string.description_label),
        stringResource(id = R.string.description_placeholder),
        Icons.Default.Email,
        RoundedCornerShape(NO_ROUND_CORNER.dp),
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        descriptionTextChangeListener,
        onDoneClickListener = onDoneClickListener,
        modifier = modifier
    )
}