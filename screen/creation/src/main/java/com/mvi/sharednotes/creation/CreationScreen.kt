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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvi.sharednotes.creation.view.NewNoteViewModel
import com.mvi.sharednotes.creation.view.attributes.Event
import com.mvi.sharednotes.creation.view.attributes.State
import com.mvi.sharednotes.creation.view.composables.DescriptionField
import com.mvi.sharednotes.creation.view.composables.DropDownField
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
fun CreationScreen(
    viewModel: NewNoteViewModel,
    navigateUp: () -> Unit
) {
    val effect by viewModel.effect.collectAsStateWithLifecycle()
    LaunchedEffect(effect) {
        when {
            effect.transitionNotes -> navigateUp()
            else -> Unit
        }
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    val titleTextChangeListener: (String) -> Unit = {
        viewModel.dispatch(Event.TitleUpdate(it))
    }
    val descriptionTextChangeListener: (String) -> Unit = {
        viewModel.dispatch(Event.DescriptionUpdate(it))
    }
    val onDropDownPickListener: (String) -> Unit = {
        viewModel.dispatch(Event.TagUpdate(it))
    }
    val onSubmitClickListener: () -> Unit = {
        viewModel.dispatch(Event.Submit)
    }
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
                onDropDownPickListener,
                onSubmitClickListener
            )
        }
    }
}

@Suppress("LongMethod")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewNoteLayout(
    state: State,
    titleTextChangeListener: (String) -> Unit,
    descriptionTextChangeListener: (String) -> Unit,
    onDropDownPickListener: (String) -> Unit,
    onSubmitClickListener: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        val (title, description, button, progress, dropdown) = remember { createRefs() }
        val (titleFocusRef, descriptionFocusRef) = remember { FocusRequester.createRefs() }
        ProgressIndicator(
            state.isLoading,
            Modifier
                .constrainAs(progress) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
        )

        DropDownField(
            state,
            stringArrayResource(R.array.note_types),
            Modifier
                .constrainAs(dropdown) {
                    top.linkTo(parent.top, PADDING.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(HORIZONTAL_PADDING.dp, VERTICAL_PADDING.dp)
                .fillMaxWidth(),
            onDropDownPickListener
        )

        TitleField(
            state,
            Modifier
                .fillMaxWidth()
                .focusRequester(titleFocusRef)
                .focusProperties { next = descriptionFocusRef }
                .constrainAs(title) {
                    top.linkTo(dropdown.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(HORIZONTAL_PADDING.dp, VERTICAL_PADDING.dp),
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
                    top.linkTo(description.bottom, PADDING.dp)
                    bottom.linkTo(parent.bottom, PADDING.dp)
                }
        )
    }
}