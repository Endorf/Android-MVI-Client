package com.mvi.sharednotes.creation.view.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mvi.sharednotes.creation.R
import com.mvi.sharednotes.ui.core.BaseButton
import com.mvi.sharednotes.ui.core.NO_ROUND_CORNER
import com.mvi.sharednotes.ui.core.ROUND_CORNER

@Composable
fun SubmitButton(
    isLoading: Boolean,
    onLoginClickListener: () -> Unit,
    modifier: Modifier
) {
    BaseButton(
        isLoading,
        stringResource(id = R.string.submit_button_title),
        modifier,
        RoundedCornerShape(
            NO_ROUND_CORNER.dp,
            NO_ROUND_CORNER.dp,
            ROUND_CORNER.dp,
            ROUND_CORNER.dp
        ),
        onLoginClickListener
    )
}