package com.mvi.sharednotes.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mvi.sharednotes.ui.core.BaseButton
import com.mvi.sharednotes.ui.core.BaseOutlinedTextField
import com.mvi.sharednotes.ui.core.BaseProgressIndicator

@Composable
fun LoginScreen(
    onNoteListEnter: () -> Unit
) {
    val textChangeListener: (String) -> Unit = {}
    val onLoginClickListener: () -> Unit = { onNoteListEnter() }

    LoginLayout(textChangeListener, onLoginClickListener)
}

@Composable
fun LoginLayout(
    textChangeListener: (String) -> Unit,
    onLoginClickListener: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (text, button, progress) = createRefs()

        EmailView(
            "",
            false,
            false,
            textChangeListener,
            onLoginClickListener,
            Modifier
                .constrainAs(text) {
                    top.linkTo(button.top, margin = (-100).dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )

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

        LoginButton(
            false,
            onLoginClickListener,
            Modifier
                .constrainAs(button) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
    }

}


@Composable
fun EmailView(
    value: String,
    isLoading: Boolean,
    hasError: Boolean,
    textChangeListener: (String) -> Unit,
    onDoneClickListener: () -> Unit,
    modifier: Modifier
) {
    BaseOutlinedTextField(
        value,
        isLoading,
        hasError,
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
