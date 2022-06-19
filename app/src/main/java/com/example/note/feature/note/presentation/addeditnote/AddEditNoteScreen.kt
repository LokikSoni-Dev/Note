package com.example.note.feature.note.presentation.addeditnote

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.presentation.addeditnote.components.TransparentTextField
import com.example.note.feature.note.presentation.util.Constants.DURATION

/**
 * Screen to add and edit note like note title, content, and color.
 *
 * @author Lokik Soni
 * Created On 12-06-2022
 */
@Composable
fun AddEditNoteScreen(
	navController: NavController,
	viewModel: AddEditNoteViewModel,
	noteColor: Int
) {
	val shape = remember { RoundedCornerShape(10.dp) }
	val scaffoldState = rememberScaffoldState()
	val addEditNoteState by viewModel.addEditNoteState.collectAsState()
	val noteBackgroundColor = remember {
		Animatable(
			initialValue = Color(if (noteColor != -1) noteColor else addEditNoteState.color)
		)
	}

	// Animate the color when there is a change in color.
	val color = addEditNoteState.color
	LaunchedEffect(scaffoldState, color) {
		noteBackgroundColor.animateTo(
			targetValue = Color(color),
			animationSpec = tween(durationMillis = DURATION)
		)
	}

	// Back to the NoteScree when we save note
	val isSaved = addEditNoteState.isNoteSaved
	LaunchedEffect(scaffoldState, isSaved) {
		if (isSaved) navController.navigateUp()
	}

	// SHow the Snackbar when there is message.
	addEditNoteState.errorMsg?.let { message ->
		LaunchedEffect(scaffoldState, message) {
			scaffoldState.snackbarHostState.showSnackbar(message = message)
			viewModel.onEvent(AddEditNoteEvent.MessageShown)
		}
	}

	Scaffold(
		modifier = Modifier
			.fillMaxSize(),
		scaffoldState = scaffoldState,
		floatingActionButton = {
			FloatingActionButton(
				onClick = {
					viewModel.onEvent(AddEditNoteEvent.SaveNote)
				},
				backgroundColor = MaterialTheme.colors.primary
			) {
				Icon(
					imageVector = Icons.Rounded.Save,
					contentDescription = stringResource(R.string.save_note)
				)
			}
		},
		backgroundColor = noteBackgroundColor.value,
		contentColor = MaterialTheme.colors.background
	) {
		Column(
			modifier = Modifier
				.padding(16.dp),
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = stringResource(R.string.note_color),
					style = MaterialTheme.typography.h4
				)
				IconButton(
					onClick = { viewModel.onEvent(AddEditNoteEvent.ToggleColorSection) }
				) {
					Icon(
						imageVector = Icons.Rounded.Sort,
						contentDescription = stringResource(R.string.sort)
					)
				}
			}
			AnimatedVisibility(
				visible = addEditNoteState.isColorSectionVisible
			) {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding(8.dp),
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Note.noteColors.forEach { color ->
						val colorInt = color.toArgb()
						Box(
							modifier = Modifier
								.size(50.dp)
								.shadow(elevation = 15.dp, shape = shape)
								.clip(shape)
								.background(color)
								.border(
									width = 2.dp,
									color = if (addEditNoteState.color == colorInt)
										Color.Black
									else
										Color.Transparent,
									shape = shape
								)
								.clickable {
									viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
								}
						)
					}
				}
			}
			Spacer(modifier = Modifier.height(16.dp))
			TransparentTextField(
				text = addEditNoteState.title,
				hint = stringResource(R.string.enter_title),
				singleLine = true,
				textStyle = MaterialTheme.typography.h5,
				onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnterTitle(it)) }
			)
			Spacer(modifier = Modifier.height(16.dp))
			TransparentTextField(
				modifier = Modifier.weight(1f),
				text = addEditNoteState.content,
				hint = stringResource(R.string.enter_content),
				textStyle =  MaterialTheme.typography.body1,
				onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnterContent(it)) }
			)
		}
	}
}