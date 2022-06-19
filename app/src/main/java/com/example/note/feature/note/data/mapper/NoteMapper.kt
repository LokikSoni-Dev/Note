package com.example.note.feature.note.data.mapper

import com.example.note.feature.note.data.local.entity.NoteEntity
import com.example.note.feature.note.domain.model.Note

/**
 * Map the Local storage data to the data used for UI.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
fun NoteEntity.toNote(): Note {
	return Note(
		title = title,
		content = content,
		timeStamp = timeStamp,
		color = color,
		id = id
	)
}

/**
 * Map the user input data to the [NoteEntity]
 * to store in DB.
 */
fun Note.toNoteEntity(): NoteEntity {
	return NoteEntity(
		title = title,
		content = content,
		timeStamp = timeStamp,
		color = color,
		id = id
	)
}