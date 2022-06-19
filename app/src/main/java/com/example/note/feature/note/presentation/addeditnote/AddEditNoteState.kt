package com.example.note.feature.note.presentation.addeditnote

import androidx.compose.ui.graphics.toArgb
import com.example.note.feature.note.domain.model.Note

/**
 * Holds all possible states for the [AddEditNoteScreen]
 * including initial state.
 *
 * @author Lokik Soni
 * Created On 12-06-2022
 */
data class AddEditNoteState(
	val title: String = "",
	val content: String = "",
	val color: Int = Note.noteColors.random().toArgb(),
	val isColorSectionVisible: Boolean = false,
	val isNoteSaved: Boolean = false,
	val errorMsg: String? = null
)