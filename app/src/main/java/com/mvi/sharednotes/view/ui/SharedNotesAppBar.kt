package com.mvi.sharednotes.view.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mvi.sharednotes.R
import com.mvi.sharednotes.theme.TopAppBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedNotesAppBar(
    appBarInfo: Route,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    onLogoutAction: () -> Unit = {}
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
                colors = TopAppBarColor.centerAlignedTopAppBarColors(),
                actions = appBarInfo.toActions(
                    onLogOut = onLogoutAction
                )
            )
        }
    )
}