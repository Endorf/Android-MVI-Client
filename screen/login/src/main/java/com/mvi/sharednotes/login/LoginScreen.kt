package com.mvi.sharednotes.login

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvi.sharednotes.login.view.LoginViewModel
import com.mvi.sharednotes.login.view.attributes.Event
import com.mvi.sharednotes.login.view.attributes.State
import com.mvi.sharednotes.login.view.composables.EmailView
import com.mvi.sharednotes.login.view.composables.LoginButton
import com.mvi.sharednotes.login.view.composables.ProgressIndicator
import com.mvi.sharednotes.login.view.graphics.RadialShaderBrush

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
internal const val EMAIL_TAG = "email_field"

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
internal const val LOGIN_BUTTON_TAG = "login_button"

private const val LOGIN_CONTAINER_HEIGHT = 290
private const val LOGIN_BUTTON_TOP_MARGIN = 35
private const val LOGIN_EMAIL_MARGIN = -90
private const val HORIZONTAL_PADDING = 16
private const val CARD_PADDING = 16
private const val TITLE_VERTICAL_PADDING = 16
private const val VIEW_VERTICAL_PADDING = 0
private const val ROUNDED_CORNER_SHAPE = 8

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNoteListEnter: () -> Unit
) {
    val effect by viewModel.effect.collectAsStateWithLifecycle()
    LaunchedEffect(effect) {
        when {
            effect.transitionNotes -> onNoteListEnter()
            else -> Unit
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    val textChangeListener: (String) -> Unit = {
        viewModel.dispatch(Event.EmailUpdate(it))
    }
    val onLoginClickListener: () -> Unit = { viewModel.dispatch(Event.Login) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(RadialShaderBrush)
    ) {
        Card(
            shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE.dp),
            modifier = Modifier
                .padding(CARD_PADDING.dp)
                .fillMaxWidth()
        ) {
            LoginLayout(state, textChangeListener, onLoginClickListener)
        }
    }

}

@Composable
@Suppress("DestructuringDeclarationWithTooManyEntries", "LongMethod")
fun LoginLayout(
    state: State,
    textChangeListener: (String) -> Unit,
    onLoginClickListener: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .heightIn(min = LOGIN_CONTAINER_HEIGHT.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (title, text, button, progress) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(HORIZONTAL_PADDING.dp, TITLE_VERTICAL_PADDING.dp),
            text = stringResource(id = R.string.login_title_hint),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        EmailView(
            state,
            textChangeListener,
            onLoginClickListener,
            Modifier
                .constrainAs(text) {
                    top.linkTo(button.top, LOGIN_EMAIL_MARGIN.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(HORIZONTAL_PADDING.dp, VIEW_VERTICAL_PADDING.dp)
                .testTag(EMAIL_TAG)
        )

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

        LoginButton(
            state.isLoading,
            onLoginClickListener,
            Modifier
                .constrainAs(button) {
                    top.linkTo(title.bottom, LOGIN_BUTTON_TOP_MARGIN.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(HORIZONTAL_PADDING.dp, VIEW_VERTICAL_PADDING.dp)
                .testTag(LOGIN_BUTTON_TAG)
        )
    }
}
