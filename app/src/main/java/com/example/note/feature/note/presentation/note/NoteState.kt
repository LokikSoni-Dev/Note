package com.example.note.feature.note.presentation.note

import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.domain.util.NoteOrder

/**
 * Holds all possible states for the [NotesScreen]
 * including initial state.
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
data class NoteState(
	val notes: List<Note> = emptyList(),
	val noteOrder: NoteOrder = NoteOrder.Date(NoteOrder.OrderType.DEC),
	val isOrderSectionVisible: Boolean = false,
	val errorMsg: String? = null
)
