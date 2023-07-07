package com.mvi.sharednotes.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvi.sharednotes.notes.attributes.Event
import com.mvi.sharednotes.notes.view.entity.Note
import com.mvi.sharednotes.notes.view.NotesViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mvi.sharednotes.notes.attributes.State
import com.mvi.sharednotes.notes.view.composables.NoteItemRow
import com.mvi.sharednotes.ui.core.graphics.RadialShaderBrush
import com.mvi.sharednotes.notes.view.composables.ProgressIndicator
import com.mvi.sharednotes.ui.core.BaseFloatingActionButton

private const val FAB_PADDING = 16

@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    onCreationEnter: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.dispatch(Event.GetNotes)
    }
    val refresh: () -> Unit = {
        viewModel.dispatch(Event.Refresh)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RadialShaderBrush)
    ) {
        NotesList(state, refresh)
        Fab(
            Modifier
                .padding(FAB_PADDING.dp)
                .align(Alignment.BottomEnd),
            onCreationEnter
        )
        ProgressIndicator(
            state.isLoading,
            Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Fab(modifier: Modifier, onClick: () -> Unit) {
    BaseFloatingActionButton(
        modifier,
        stringResource(id = R.string.fab_content_description),
        Icons.Rounded.Edit,
        onClick
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotesList(state: State, refresh: () -> Unit) {
    val pullRefreshState = rememberPullRefreshState(state.isRefreshing, { refresh() })
    val notes: List<Note> = state.notes

    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn {
            items(notes) { note ->
                NoteItemRow(note)
            }
        }
        PullRefreshIndicator(
            state.isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter),
            MaterialTheme.colorScheme.tertiary,
            MaterialTheme.colorScheme.onTertiary
        )
    }
}