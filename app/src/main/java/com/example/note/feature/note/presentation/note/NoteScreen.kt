package com.example.note.feature.note.presentation.note

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.feature.note.presentation.navigation.Screen
import com.example.note.feature.note.presentation.note.components.NoteItem
import com.example.note.feature.note.presentation.note.components.OrderSection

/**
 * Shows the list of notes with sorting order
 * and save note options.
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
@Composable
fun NotesScreen(
	navController: NavController,
	viewModel: NoteViewModel
) {
	val noteState by viewModel.noteState.collectAsState()
    val scaffoldState = rememberScaffoldState()

	noteState.errorMsg?.let { message ->
		val undoMessageText = stringResource(R.string.undo)

		LaunchedEffect(scaffoldState, undoMessageText, message) {
			val result = scaffoldState.snackbarHostState.showSnackbar(
				message = message,
				actionLabel = undoMessageText
			)

			if (result == SnackbarResult.ActionPerformed) {
				viewModel.onEvent(NotesEvent.RestoreNote)
			}
			viewModel.onEvent(NotesEvent.MessageShown)
		}
	}

	Scaffold(
		modifier = Modifier
			.fillMaxSize(),
		scaffoldState = scaffoldState,
		floatingActionButton = {
			FloatingActionButton(
				onClick = {
					navController.navigate(Screen.AddEditNote.route)
				},
				backgroundColor = MaterialTheme.colors.primary
			) {
				Icon(
					imageVector = Icons.Rounded.Add,
					contentDescription = stringResource(R.string.add_note)
				)
			}
		},
	) {
		Column(
			modifier = Modifier
				.padding(16.dp)
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = stringResource(R.string.your_note),
					style = MaterialTheme.typography.h4
				)
				IconButton(
					onClick = { viewModel.onEvent(NotesEvent.ToggleOrderSection) }
				) {
					Icon(
						imageVector = Icons.Rounded.Sort,
						contentDescription = stringResource(R.string.sort)
					)
				}
			}
			AnimatedVisibility(
				visible = noteState.isOrderSectionVisible
			) {
				OrderSection(
					modifier = Modifier
						.fillMaxWidth()
						.padding(16.dp),
					noteOrder = noteState.noteOrder,
					onOrderChange = {
						viewModel.onEvent(NotesEvent.Order(it))
					}
				)
			}
			Spacer(modifier = Modifier.height(16.dp))
			LazyColumn {
				items(noteState.notes) { note ->
					NoteItem(
						modifier = Modifier
							.fillMaxWidth()
							.clickable {
								navController.navigate(
									Screen.AddEditNote.route + "?noteId=${note.id}&colorId=${note.color}"
								)
							},
						note = note,
						onDeleteClick = {
							viewModel.onEvent(NotesEvent.DeleteNote(note))
						}
					)
					Spacer(modifier = Modifier.height(16.dp))
				}
			}
		}
	}
}