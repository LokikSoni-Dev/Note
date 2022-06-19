package com.example.note.feature.note.domain.usecase

import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.domain.repository.NoteRepository
import com.example.note.feature.note.domain.usecase.base.BaseUseCase
import com.example.note.feature.note.domain.util.NoteOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Get the notes from DB using [_repository] and apply
 * the required [NoteOrder] to the List of [Note].
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
@Singleton
class GetNotesUseCase @Inject constructor(
	private val _repository: NoteRepository
): BaseUseCase<NoteOrder, Flow<List<Note>>>() {

	override fun invoke(parameter: NoteOrder): Flow<List<Note>> {
		return _repository.getNotes().map { notes ->

			when(parameter.orderType) {

				NoteOrder.OrderType.ASC -> {
					when(parameter) {
						is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
						is NoteOrder.Date -> notes.sortedBy { it.timeStamp }
						is NoteOrder.Color -> notes.sortedBy { it.color }
					}
				}
				NoteOrder.OrderType.DEC -> {
					when(parameter) {
						is NoteOrder.Title -> notes.sortedByDescending { it.title }
						is NoteOrder.Date -> notes.sortedByDescending { it.timeStamp }
						is NoteOrder.Color -> notes.sortedByDescending { it.color }
					}
				}
			}
		}
	}
}