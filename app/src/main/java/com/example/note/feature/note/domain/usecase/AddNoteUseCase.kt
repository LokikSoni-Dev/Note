package com.example.note.feature.note.domain.usecase

import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.domain.repository.NoteRepository
import com.example.note.feature.note.domain.usecase.base.SuspendUseCase
import com.example.note.feature.note.presentation.util.NoteAppException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.jvm.Throws

/**
 * Add the note to the DB using [_repository].
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
@Singleton
class AddNoteUseCase @Inject constructor(
	private val _repository: NoteRepository
): SuspendUseCase<Note, Unit>() {

	@Throws(NoteAppException.InvalidNoteException::class)
	override suspend fun invoke(parameter: Note) {

		if (parameter.title.isBlank()) {
			throw NoteAppException.InvalidNoteException("The title of the note can't be empty.")
		}
		if (parameter.content.isBlank()) {
			throw NoteAppException.InvalidNoteException("The content of the note can't be empty.")
		}

		_repository.insertNote(parameter)
	}
}