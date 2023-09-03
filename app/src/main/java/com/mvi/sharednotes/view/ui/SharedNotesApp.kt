package com.mvi.sharednotes.view.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mvi.sharednotes.creation.CreationScreen
import com.mvi.sharednotes.login.LoginScreen
import com.mvi.sharednotes.notes.NotesScreen
import com.mvi.sharednotes.view.attributes.State
import com.mvi.sharednotes.view.ui.animation.enterTransition
import com.mvi.sharednotes.view.ui.animation.exitTransition
import com.mvi.sharednotes.view.ui.animation.popEnterTransition
import com.mvi.sharednotes.view.ui.animation.popExitTransition

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SharedNotesApp(state: State) {
    val navController = rememberAnimatedNavController()
    SharedNotesNavHost(
        navController,
        state = state
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SharedNotesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    state: State
) {
    val startDestination = when {
        state.isLoading -> return
        state.isUserAuthenticated -> Route.HOME.name
        else -> Route.LOGIN.name
    }

    val onNoteListEnter = { getNoteListNavigation(navController) }
    val onLoginEnter = { getLoginNavigation(navController) }
    val onCreationEnter = { navController.navigate(Route.CREATION.name) }

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = Route.valueOf(
        backStackEntry?.destination?.route ?: Route.HOME.name
    )

    val navigateUp: () -> Unit = { navController.navigateUp() }

    var showLogoutDialog by rememberSaveable { mutableStateOf(false) }

    if (showLogoutDialog) {
        LogoutDialog(
            onDismiss = { showLogoutDialog = false },
            onLogout = onLoginEnter
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = {
            SharedNotesAppBar(
                currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = navigateUp,
                onLogoutAction = { showLogoutDialog = true }
            )
        }
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(it),
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
            composable(Route.LOGIN.name) { LoginScreen(hiltViewModel(), onNoteListEnter) }
            composable(Route.HOME.name) { NotesScreen(hiltViewModel(), onCreationEnter) }
            composable(Route.CREATION.name) { CreationScreen(hiltViewModel(), navigateUp) }
        }
    }
}

private fun getNoteListNavigation(navController: NavHostController) =
    navController.navigate(Route.HOME.name) {
        popUpTo(Route.LOGIN.name) {
            inclusive = true
        }
    }

private fun getLoginNavigation(navController: NavHostController) =
    navController.navigate(Route.LOGIN.name) {
        popUpTo(Route.HOME.name) {
            inclusive = true
        }
    }