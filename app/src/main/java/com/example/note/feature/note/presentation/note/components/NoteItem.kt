package com.example.note.feature.note.presentation.note.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.note.R
import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.presentation.note.NotesScreen

/**
 * Composable to show notes in [NotesScreen].
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
@Composable
fun NoteItem(
	modifier: Modifier = Modifier,
	note: Note,
	cornerRadius: Dp = 15.dp,
	onDeleteClick: () -> Unit
) {
	Card(
		modifier = modifier,
		shape = RoundedCornerShape(cornerRadius),
		backgroundColor = Color(note.color).compositeOver(Color.White),
		contentColor = MaterialTheme.colors.onSurface
	) {
		Box(modifier = Modifier.fillMaxSize()) {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(16.dp)
					.padding(end = 32.dp),
			) {
				Text(
					text = note.title,
					style = MaterialTheme.typography.h6,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
				Spacer(modifier = Modifier.height(8.dp))
				Text(
					text = note.content,
					style = MaterialTheme.typography.body1,
					maxLines = 10,
					overflow = TextOverflow.Ellipsis
				)
			}
			IconButton(
				onClick = onDeleteClick,
				modifier = Modifier.align(Alignment.BottomEnd)
			) {
				Icon(
					imageVector = Icons.Rounded.Delete,
					contentDescription = stringResource(R.string.delete_note)
				)
			}
		}
	}
}