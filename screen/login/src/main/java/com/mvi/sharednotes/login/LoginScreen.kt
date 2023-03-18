package com.mvi.sharednotes.login

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvi.sharednotes.login.view.LoginViewModel
import com.mvi.sharednotes.login.view.attributes.Event
import com.mvi.sharednotes.login.view.attributes.State
import com.mvi.sharednotes.ui.core.BaseButton
import com.mvi.sharednotes.ui.core.BaseOutlinedTextField
import com.mvi.sharednotes.ui.core.BaseProgressIndicator

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
internal const val EMAIL_TAG = "email_field"

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
internal const val LOGIN_BUTTON_TAG = "login_button"

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

    LoginLayout(state, textChangeListener, onLoginClickListener)
}

@Composable
fun LoginLayout(
    state: State,
    textChangeListener: (String) -> Unit,
    onLoginClickListener: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (text, button, progress) = createRefs()

        val emailMargin = dimensionResource(R.dimen.login_email_field_margin)
        val defaultPadding = dimensionResource(R.dimen.default_padding)

        EmailView(
            state,
            textChangeListener,
            onLoginClickListener,
            Modifier
                .constrainAs(text) {
                    top.linkTo(button.top, emailMargin)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(defaultPadding, 0.dp)
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
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(defaultPadding, 0.dp)
                .testTag(LOGIN_BUTTON_TAG)
        )
    }
}

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
        stringResource(id = R.string.login_email_hint),
        stringResource(id = R.string.login_email_hint),
        Icons.Default.Email,
        textChangeListener,
        onDoneClickListener,
        modifier
    )
}

@Composable
fun ProgressIndicator(
    isLoading: Boolean,
    modifier: Modifier
) {
    BaseProgressIndicator(
        isLoading,
        modifier
    )
}

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
