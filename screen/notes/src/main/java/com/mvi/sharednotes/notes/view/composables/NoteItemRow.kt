package com.mvi.sharednotes.notes.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mvi.sharednotes.notes.R
import com.mvi.sharednotes.notes.view.entity.Note

private const val ROUNDED_CORNER_SHAPE = 8
private const val CARD_PADDING = 4
private const val BACKGROUND_CARD_ALPHA = 0.9f
private const val PADDING = 8
private const val IMAGE_SIZE = 45
private const val NO_PADDING = 0
private const val TITLE_HORIZONTAL_PADDING = 0
private const val DIVIDER_HEIGHT = 1
private const val ROUNDED_CORNER_BOX = 6
private const val HORIZONTAL_TEXT_PADDING = 6

@Composable
fun NoteItemRow(note: Note) {
    Card(
        shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE.dp),
        modifier = Modifier
            .padding(CARD_PADDING.dp)
            .fillMaxWidth()
            .alpha(BACKGROUND_CARD_ALPHA)
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
                modifier = Modifier.padding(NO_PADDING.dp, PADDING.dp, PADDING.dp, PADDING.dp)
            ) {
                UserBlock(note.author, note.tag)
                ContentBlock(note.title ?: "", note.description ?: "")
            }
        }
    }
}

@Composable
fun UserBlock(author: String, tag: String) {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            author,
            Modifier.padding(NO_PADDING.dp, NO_PADDING.dp, PADDING.dp, NO_PADDING.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelLarge
        )
        Box(
            Modifier
                .clip(shape = RoundedCornerShape(ROUNDED_CORNER_BOX.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                tag,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(HORIZONTAL_TEXT_PADDING.dp, NO_PADDING.dp)
            )
        }
    }
}

@Composable
fun ContentBlock(title: String, description: String) {
    Text(
        title,
        Modifier.padding(TITLE_HORIZONTAL_PADDING.dp, PADDING.dp),
        style = MaterialTheme.typography.titleMedium
    )

    if (description.isNotEmpty()) {
        Divider(
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .fillMaxWidth()
                .width(DIVIDER_HEIGHT.dp)
        )
        Text(
            description,
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic
        )
    }
}