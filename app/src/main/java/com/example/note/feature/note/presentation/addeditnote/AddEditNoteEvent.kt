package com.example.note.feature.note.presentation.addeditnote

/**
 * Holds all possible events used in [AddEditNoteScreen].
 *
 * @author Lokik Soni
 * Created On 12-06-2022
 */
sealed class AddEditNoteEvent {
	data class EnterTitle(val title: String): AddEditNoteEvent()
	data class EnterContent(val content: String): AddEditNoteEvent()
	data class ChangeColor(val color: Int): AddEditNoteEvent()
	object SaveNote: AddEditNoteEvent()
	object ToggleColorSection: AddEditNoteEvent()
	object MessageShown: AddEditNoteEvent()
}
