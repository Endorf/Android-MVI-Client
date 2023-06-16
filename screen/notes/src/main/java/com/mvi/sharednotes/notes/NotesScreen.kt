package com.mvi.sharednotes.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvi.sharednotes.notes.attributes.Event
import com.mvi.sharednotes.notes.view.entity.Note
import com.mvi.sharednotes.notes.view.NotesViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mvi.sharednotes.notes.graphics.RadialShaderBrush
import com.mvi.sharednotes.notes.view.composables.ProgressIndicator

private const val IMAGE_SIZE = 45
private const val ROUNDED_CORNER_SHAPE = 8
private const val PADDING = 8
private const val TITLE_HORIZONTAL_PADDING = 0
private const val CARD_PADDING = 4

@Composable
fun NotesScreen(
    viewModel: NotesViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.dispatch(Event.GetNotes)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RadialShaderBrush)
    ) {
        NotesList(state.notes)
        ProgressIndicator(
            state.isLoading,
            Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun NotesList(notes: List<Note>) {
    LazyColumn {
        items(notes) { note ->
            ItemRow(note)
        }
    }
}

@Composable
fun ItemRow(note: Note) {
    Card(
        shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE.dp),
        modifier = Modifier
            .padding(CARD_PADDING.dp)
            .fillMaxWidth()
            .alpha(0.9f)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = painterResource(id = R.drawable.ava),
                contentDescription = stringResource(id = R.string.user),
                Modifier
                    .padding(PADDING.dp)
                    .width(IMAGE_SIZE.dp)
                    .height(IMAGE_SIZE.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(0.dp, PADDING.dp, PADDING.dp, PADDING.dp)
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        note.author,
                        Modifier.padding(0.dp, 0.dp, PADDING.dp, 0.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(note.tag)
                }
                Text(
                    note.title?:"",
                    Modifier.padding(TITLE_HORIZONTAL_PADDING.dp, PADDING.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    note.description?:"",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}