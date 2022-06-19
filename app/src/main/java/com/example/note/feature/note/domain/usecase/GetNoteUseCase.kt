package com.example.note.feature.note.domain.usecase

import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.domain.repository.NoteRepository
import com.example.note.feature.note.domain.usecase.base.SuspendUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Get the note from DB for 'id' using [_repository].
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
@Singleton
class GetNoteUseCase @Inject constructor(
	private val _repository: NoteRepository
): SuspendUseCase<Int, Note>() {

	override suspend fun invoke(parameter: Int) = _repository.getNoteByID(id = parameter)
}