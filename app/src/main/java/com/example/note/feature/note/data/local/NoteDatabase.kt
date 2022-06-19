package com.example.note.feature.note.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note.feature.note.data.local.entity.NoteEntity

/**
 * [NoteDao] use [NoteDatabase] to perform
 * CRUD operation on data(entity).
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
@Database(
	entities = [NoteEntity::class],
	version = 1
)
abstract class NoteDatabase: RoomDatabase() {

	abstract val noteDao: NoteDao

	companion object {
		const val DATABASE_NAME = "note_db"
	}
}