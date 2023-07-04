package com.mvi.sharednotes.creation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mvi.sharednotes.creation.view.attributes.State
import com.mvi.sharednotes.creation.view.composables.DescriptionField
import com.mvi.sharednotes.creation.view.composables.ProgressIndicator
import com.mvi.sharednotes.creation.view.composables.SubmitButton
import com.mvi.sharednotes.creation.view.composables.TitleField
import com.mvi.sharednotes.ui.core.graphics.RadialShaderBrush

private const val PADDING = 16
private const val FIELD_MARGIN = 90
private const val ROUNDED_CORNER_SHAPE = 8
private const val CARD_PADDING = 4
private const val BACKGROUND_CARD_ALPHA = 0.9f
private const val VERTICAL_PADDING = 0
private const val HORIZONTAL_PADDING = PADDING

@Composable
fun CreationScreen() {
    val state = State.create()
    val titleTextChangeListener: (String) -> Unit = {}
    val descriptionTextChangeListener: (String) -> Unit = {}
    val onSubmitClickListener: () -> Unit = {}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RadialShaderBrush)
    ) {
        Card(
            shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE.dp),
            modifier = Modifier
                .padding(CARD_PADDING.dp)
                .fillMaxWidth()
                .alpha(BACKGROUND_CARD_ALPHA)
        ) {
            NewNoteLayout(
                state,
                titleTextChangeListener,
                descriptionTextChangeListener,
                onSubmitClickListener
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewNoteLayout(
    state: State,
    titleTextChangeListener: (String) -> Unit,
    descriptionTextChangeListener: (String) -> Unit,
    onSubmitClickListener: () -> Unit
) {
    val (titleFocusRef, descriptionFocusRef) = remember { createRefs() }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        val (title, description, button, progress) = remember { createRefs() }
        ProgressIndicator(
            true,
            Modifier
                .constrainAs(progress) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
        )

        TitleField(
            state, Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top, PADDING.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(HORIZONTAL_PADDING.dp, VERTICAL_PADDING.dp)
                .fillMaxWidth()
                .focusRequester(titleFocusRef)
                .focusProperties { next = descriptionFocusRef },
            titleTextChangeListener
        )

        DescriptionField(
            state,
            Modifier
                .constrainAs(description) {
                    top.linkTo(title.top, FIELD_MARGIN.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(HORIZONTAL_PADDING.dp, VERTICAL_PADDING.dp)
                .fillMaxWidth()
                .focusRequester(descriptionFocusRef),
            descriptionTextChangeListener,
            onSubmitClickListener
        )

        SubmitButton(
            state.isLoading,
            onSubmitClickListener,
            Modifier
                .padding(HORIZONTAL_PADDING.dp, VERTICAL_PADDING.dp)
                .fillMaxWidth()
                .constrainAs(button) {
                    top.linkTo(description.top, FIELD_MARGIN.dp)
                    bottom.linkTo(parent.bottom, PADDING.dp)
                }
        )
    }
    /*Column(
        modifier = Modifier.padding(PADDING.dp, PADDING.dp, PADDING.dp, PADDING.dp)
    ) {
        ProgressIndicator(
            true,
            Modifier
                .fillMaxWidth()
        )
        TitleField(
            state, Modifier
                .fillMaxWidth()
                .focusRequester(title)
                .focusProperties { next = description },
            titleTextChangeListener
        )
        DescriptionField(
            state,
            Modifier
                .fillMaxWidth()
                .focusRequester(description),
            descriptionTextChangeListener,
            onSubmitClickListener
        )
        SubmitButton(
            state.isLoading,
            onSubmitClickListener,
            Modifier.fillMaxWidth()
        )
    }

     */
}



