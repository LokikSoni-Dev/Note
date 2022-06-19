package com.example.note.feature.note.presentation.note

import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.domain.util.NoteOrder

/**
 * Holds all possible events used in [NotesScreen].
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
sealed class NotesEvent {
	data class Order(val noteOrder: NoteOrder): NotesEvent()
	object ToggleOrderSection: NotesEvent()
	data class DeleteNote(val note: Note): NotesEvent()
	object RestoreNote: NotesEvent()
	object MessageShown: NotesEvent()
}
