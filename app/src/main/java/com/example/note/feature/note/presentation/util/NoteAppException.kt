package com.example.note.feature.note.presentation.util

/**
 * All possible [Exception] that can be thrown by NoteApp.
 *
 * @author Lokik Soni
 * Created On 12-06-2022
 */
sealed class NoteAppException(val msg: String): Exception(msg) {

	class InvalidNoteException(data: String): NoteAppException(data)
}