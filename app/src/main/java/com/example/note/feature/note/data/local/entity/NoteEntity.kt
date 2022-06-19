package com.example.note.feature.note.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * An Entity class to store note data into
 * the room database.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
@Entity
data class NoteEntity(
	val title: String,
	val content: String,
	val timeStamp: Long,
	val color: Int,
	@PrimaryKey val id: Int? = null
)
