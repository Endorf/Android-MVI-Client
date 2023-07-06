package com.mvi.sharednotes.view.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mvi.sharednotes.R
import com.mvi.sharednotes.creation.CreationScreen
import com.mvi.sharednotes.login.LoginScreen
import com.mvi.sharednotes.notes.NotesScreen
import com.mvi.sharednotes.theme.TopAppBarColor
import com.mvi.sharednotes.view.attributes.State

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SharedNotesApp(state: State) {
    val navController = rememberAnimatedNavController()
    SharedNotesNavHost(
        navController,
        state = state
    )
}

private const val INITIAL_OFFSET_X = 1500
private const val SLIDE_IN_DURATION = 300
private const val SLIDE_OUT_DURATION = 150

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SharedNotesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    state: State
) {
    val startDestination = when (state) {
        is State.Initialized -> if (state.isUserAuthenticated) {
            Route.HOME.name
        } else {
            Route.LOGIN.name
        }
        State.Loading -> return
    }
    val onNoteListEnter = {
        navController.navigate(Route.HOME.name) {
            popUpTo(Route.LOGIN.name) {
                inclusive = true
            }
        }
    }
    val onCreationEnter = {
        navController.navigate(Route.CREATION.name)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = Route.valueOf(
        backStackEntry?.destination?.route ?: Route.HOME.name
    )

    val navigateUp: () -> Unit = { navController.navigateUp() }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = {
            SharedNotesAppBar(
                currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = navigateUp
            )
        }
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(it),
            enterTransition = {
                slideInHorizontally(
                    animationSpec = keyframes { durationMillis = SLIDE_IN_DURATION },
                    initialOffsetX = { INITIAL_OFFSET_X }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = keyframes { durationMillis = SLIDE_OUT_DURATION },
                    targetOffsetX = { -INITIAL_OFFSET_X }
                )
            }
        ) {
            composable(Route.LOGIN.name) { LoginScreen(hiltViewModel(), onNoteListEnter) }
            composable(Route.HOME.name) { NotesScreen(hiltViewModel(), onCreationEnter) }
            composable(Route.CREATION.name) { CreationScreen(hiltViewModel(), navigateUp) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SharedNotesAppBar(
    appBarInfo: Route,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = appBarInfo.topBarVisibility,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(appBarInfo.titleResId)) },
                modifier = modifier
                    .shadow(14.dp)
                    .zIndex(1f),
                navigationIcon = {
                    if (canNavigateBack) {
                        IconButton(onClick = navigateUp) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.app_name)
                            )
                        }
                    }
                },
                colors = TopAppBarColor.centerAlignedTopAppBarColors()
            )
        }
    )
}