package com.example.note.feature.note.presentation.addeditnote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.domain.usecase.AddNoteUseCase
import com.example.note.feature.note.domain.usecase.GetNoteUseCase
import com.example.note.feature.note.presentation.util.NoteAppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Fetch the data from use case and map it to state
 * [AddEditNoteState] to show in UI and ensure UI is up to date
 * on configuration changes like rotation also handle the
 * events of [AddEditNoteScreen].
 *
 * @author Lokik Soni
 * Created On 12-06-2022
 */
@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val _addNoteUseCase: AddNoteUseCase,
	private val _getNoteById: GetNoteUseCase
): ViewModel() {

	private val _addEditNoteSate = MutableStateFlow(AddEditNoteState())
	val addEditNoteState = _addEditNoteSate.asStateFlow()

	private var _currentNoteId: Int? = null

	init {
		savedStateHandle.get<Int>("noteId")?.let { noteId ->
			if (noteId != -1) {
				_currentNoteId = noteId
				viewModelScope.launch {
					_getNoteById(noteId).also { note ->
						_addEditNoteSate.update { state ->
							state.copy(
								title = note.title,
								content = note.content,
								color = note.color,
							)
						}
					}
				}
			}
		}
	}

	/**
	 * Handle events of [AddEditNoteScreen].
	 */
	fun onEvent(addEditNoteEvent: AddEditNoteEvent) {

		when(addEditNoteEvent) {
			is AddEditNoteEvent.SaveNote -> {
				viewModelScope.launch {
					try {
						_addNoteUseCase(
							Note(
								title = _addEditNoteSate.value.title,
								content = _addEditNoteSate.value.content,
								timeStamp = System.currentTimeMillis(),
								color = _addEditNoteSate.value.color,
								id = _currentNoteId,
							)
						)
						_addEditNoteSate.update { it.copy(isNoteSaved = true, errorMsg = null) }

					} catch (e: NoteAppException.InvalidNoteException) {
						_addEditNoteSate.update { it.copy(isNoteSaved = false, errorMsg = e.msg) }
					}
				}
			}
			is AddEditNoteEvent.MessageShown -> {
				_addEditNoteSate.update { it.copy(errorMsg = null) }
			}
			AddEditNoteEvent.ToggleColorSection -> {
				_addEditNoteSate.update {
					it.copy(isColorSectionVisible = !it.isColorSectionVisible)
				}
			}
			is AddEditNoteEvent.ChangeColor -> {
				_addEditNoteSate.update {
					it.copy(color = addEditNoteEvent.color)
				}
			}
			is AddEditNoteEvent.EnterContent -> {
				_addEditNoteSate.update {
					it.copy(content = addEditNoteEvent.content)
				}
			}
			is AddEditNoteEvent.EnterTitle -> {
				_addEditNoteSate.update {
					it.copy(title = addEditNoteEvent.title)
				}
			}
		}
	}
}