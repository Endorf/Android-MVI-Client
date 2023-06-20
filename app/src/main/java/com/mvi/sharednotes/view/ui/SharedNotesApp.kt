package com.mvi.sharednotes.view.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.AnimatedVisibility
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
import com.mvi.sharednotes.login.LoginScreen
import com.mvi.sharednotes.notes.NotesScreen
import com.mvi.sharednotes.theme.TopAppBarColor

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SharedNotesApp() {
    val navController = rememberAnimatedNavController()
    SharedNotesNavHost(navController)
}

private const val INITIAL_OFFSET_X = 1500

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SharedNotesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Route.LOGIN.name
) {
    val onNoteListEnter = {
        navController.navigate(Route.HOME.name) {
            popUpTo(Route.LOGIN.name) {
                inclusive = true
            }
        }
    }

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = Route.valueOf(
        backStackEntry?.destination?.route ?: Route.HOME.name
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = {
            SharedNotesAppBar(
                currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(it),
            enterTransition = { slideInHorizontally(initialOffsetX = { INITIAL_OFFSET_X }) }
        ) {
            composable(Route.LOGIN.name) { LoginScreen(hiltViewModel(), onNoteListEnter) }
            composable(Route.HOME.name) { NotesScreen(hiltViewModel()) }
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