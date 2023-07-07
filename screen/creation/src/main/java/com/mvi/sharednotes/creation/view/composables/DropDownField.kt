package com.mvi.sharednotes.creation.view.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mvi.sharednotes.creation.R
import com.mvi.sharednotes.creation.view.attributes.State
import com.mvi.sharednotes.ui.core.BaseDropDownField
import com.mvi.sharednotes.ui.core.NO_ROUND_CORNER
import com.mvi.sharednotes.ui.core.ROUND_CORNER

@Composable
fun DropDownField(
    state: State,
    options: Array<String>,
    modifier: Modifier,
    userInputHandler: (String) -> Unit
) {
    BaseDropDownField(
        state.tag,
        state.isLoading,
        state.isDropDownError,
        state.errorMessage,
        options,
        RoundedCornerShape(
            ROUND_CORNER.dp,
            ROUND_CORNER.dp,
            NO_ROUND_CORNER.dp,
            NO_ROUND_CORNER.dp
        ),
        stringResource(id = R.string.tag_label),
        modifier,
        userInputHandler
    )
}