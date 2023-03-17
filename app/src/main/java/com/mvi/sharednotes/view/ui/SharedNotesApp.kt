package com.mvi.sharednotes.view.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mvi.sharednotes.R
import com.mvi.sharednotes.theme.TopAppBarColor
import com.mvi.sharednotes.ui.core.BaseButton

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SharedNotesApp() {
    val navController = rememberAnimatedNavController()
    SharedNotesNavHost(navController)
}

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
        backStackEntry?.destination?.route ?: Route.LOGIN.name
    )

    Scaffold(
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
            enterTransition = { slideInHorizontally(initialOffsetX = { 1500 }) }
        ) {
            composable(Route.LOGIN.name) {
                BaseButton(
                    false,
                    "LOGIN",
                    onNoteListEnter,
                    Modifier.fillMaxWidth()
                )
            }
            composable(Route.HOME.name) { Text(text = "HOME") }
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