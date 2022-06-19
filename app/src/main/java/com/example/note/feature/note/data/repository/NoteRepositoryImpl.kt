package com.example.note.feature.note.data.repository

import com.example.note.feature.note.data.local.NoteDao
import com.example.note.feature.note.data.mapper.toNote
import com.example.note.feature.note.data.mapper.toNoteEntity
import com.example.note.feature.note.domain.model.Note
import com.example.note.feature.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation class for [NoteRepository] to fetch data
 * from Database.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
@Singleton
class NoteRepositoryImpl @Inject constructor(
	private val _noteDao: NoteDao
): NoteRepository {

	override fun getNotes(): Flow<List<Note>> {
		return _noteDao.getNotes().map { list ->
			list.map { it.toNote() }
		}
	}

	override suspend fun getNoteByID(id: Int) = _noteDao.getNoteByID(id).toNote()

	override suspend fun insertNote(note: Note) = _noteDao.insertNote(note.toNoteEntity())

	override suspend fun deleteNote(note: Note) = _noteDao.deleteNote(note.toNoteEntity())
}

