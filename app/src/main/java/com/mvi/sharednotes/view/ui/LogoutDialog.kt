package com.mvi.sharednotes.view.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvi.sharednotes.view.AuthViewModel
import com.mvi.sharednotes.view.attributes.Event
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvi.sharednotes.view.attributes.Effect
import com.mvi.sharednotes.view.attributes.State

@Composable
fun LogoutDialog(
    onDismiss: () -> Unit,
    onLogout: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val effect by viewModel.effect.collectAsStateWithLifecycle()

    LaunchedEffect(effect) {
        when (effect) {
            Effect.ExitTransition -> {
                onDismiss()
                onLogout()
            }

            else -> Unit
        }
    }
    val state by viewModel.state.collectAsState()

    val logout = {
        viewModel.dispatch(Event.Logout)
    }


    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        title = {
            Text(
                text = "Logout",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            Text(
                text = "Are you sure?",
                style = MaterialTheme.typography.titleLarge
            )
        },
        onDismissRequest = { onDismiss() },
        dismissButton = {
            DismissButton(state, logout)
        },
        confirmButton = {
            LogoutButton(state, logout)
        }
    )
}

@Composable
fun DismissButton(state: State, onDismiss: () -> Unit) {
    Text(
        text = "Cancel",
        style = MaterialTheme.typography.labelLarge,
        color = if (state.isLoading) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                if (!state.isLoading) {
                    onDismiss()
                }
            }
    )
}

@Composable
fun LogoutButton(state: State, logout: () -> Unit) {
    Text(
        text = "Logout",
        style = MaterialTheme.typography.labelLarge,
        color = if (state.isLoading) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                if (!state.isLoading) {
                    logout()
                }
            }
    )
}