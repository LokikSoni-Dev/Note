package com.example.note.feature.note.domain.usecase

import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.domain.repository.NoteRepository
import com.example.note.feature.note.domain.usecase.base.SuspendUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Delete the not from DB using [_repository].
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
@Singleton
class DeleteNoteUseCase @Inject constructor(
	private val _repository: NoteRepository
): SuspendUseCase<Note, Unit>() {

	override suspend fun invoke(parameter: Note) = _repository.deleteNote(note = parameter)
}