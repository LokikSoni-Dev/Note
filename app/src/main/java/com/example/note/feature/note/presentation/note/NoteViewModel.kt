package com.example.note.feature.note.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.domain.usecase.AddNoteUseCase
import com.example.note.feature.note.domain.usecase.DeleteNoteUseCase
import com.example.note.feature.note.domain.usecase.GetNotesUseCase
import com.example.note.feature.note.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Fetch the data from use cases and mapping it to state
 * [NoteState] to show in UI and ensure UI is up to date
 * on configuration changes like rotation also handle the
 * events of [NotesScreen].
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
@HiltViewModel
class NoteViewModel @Inject constructor(
	private val _getNotes: GetNotesUseCase,
	private val _addNoteUseCase: AddNoteUseCase,
	private val _deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {

	private val _noteState = MutableStateFlow(NoteState())
	val noteState = _noteState.asStateFlow()

	private var _getNotesJob: Job? = null
	private var _note: Note? = null

	init {
		// Initially loads notes with default order
		getNotes()
	}

	/**
	 * Handle events of [NotesScreen].
	 */
	fun onEvent(notesEvent: NotesEvent) {

		when(notesEvent) {
			is NotesEvent.DeleteNote -> {
				viewModelScope.launch {
					_deleteNoteUseCase(notesEvent.note)
					_noteState.update { it.copy(errorMsg = "Note ${notesEvent.note.title} deleted.") }
					_note = notesEvent.note
				}
			}
			is NotesEvent.Order -> {
				// Ensure block of code not get executes again and again on click ame option.
				if (_noteState.value.noteOrder == notesEvent.noteOrder &&
					_noteState.value.noteOrder.orderType == notesEvent.noteOrder.orderType) {
					return
				}

				getNotes(notesEvent.noteOrder)
			}
			NotesEvent.RestoreNote -> {
				viewModelScope.launch {
					_addNoteUseCase(_note ?: return@launch)
					_note = null
				}
			}
			NotesEvent.ToggleOrderSection -> {
				_noteState.update { it.copy(isOrderSectionVisible = !it.isOrderSectionVisible) }
			}
			is NotesEvent.MessageShown -> {
				_noteState.update { it.copy(errorMsg = null) }
			}
		}
	}

	private fun getNotes(
		noteOrder: NoteOrder = NoteOrder.Date(NoteOrder.OrderType.DEC)
	) {
		_getNotesJob?.cancel()
		_getNotesJob = _getNotes(noteOrder).onEach { list ->
			_noteState.update { state ->
				state.copy(
					notes = list,
					noteOrder = noteOrder,
				)
			}
		}.launchIn(viewModelScope)
	}
}