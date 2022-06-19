package com.example.note.feature.note.data.local

import androidx.room.*
import com.example.note.feature.note.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

/**
 * [NoteDao] interface to define all Query methods
 * use to interact with the Database.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
@Dao
interface NoteDao {

	@Query("SELECT * from noteentity")
	fun getNotes(): Flow<List<NoteEntity>>

	@Query("SELECT * from noteentity WHERE id = :id")
	suspend fun getNoteByID(id: Int): NoteEntity

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertNote(noteEntity: NoteEntity)

	@Delete
	suspend fun deleteNote(noteEntity: NoteEntity)
}