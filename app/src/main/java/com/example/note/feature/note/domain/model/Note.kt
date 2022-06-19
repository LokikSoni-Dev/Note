package com.example.note.feature.note.domain.model

import com.example.note.ui.theme.*

/**
 * A plain kotlin data class to represent data to the UI.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
data class Note(
	val title: String,
	val content: String,
	val timeStamp: Long,
	val color: Int,
	val id: Int?
) {
	companion object {
		val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
	}
}
