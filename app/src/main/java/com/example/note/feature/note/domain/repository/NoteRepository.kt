package com.example.note.feature.note.domain.repository

import com.example.note.feature.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Provides the [Note] data from data layer to
 * the UI layer.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
interface NoteRepository {

	/**
	 * Provides the list of notes.
	 */
	fun getNotes(): Flow<List<Note>>

	/**
	 * Provides the [Note] by [id].
	 */
	suspend fun getNoteByID(id: Int): Note

	/**
	 * Insert the [note] into DB.
	 */
	suspend fun insertNote(note: Note)

	/**
	 * Delete the [note] from DB.
	 */
	suspend fun deleteNote(note: Note)
}