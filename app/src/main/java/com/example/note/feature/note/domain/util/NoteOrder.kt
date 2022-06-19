package com.example.note.feature.note.domain.util

import com.example.note.feature.note.domain.model.Note

/**
 * Holds all possible [Note] ordering type.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
sealed class NoteOrder(val orderType: OrderType) {

	// Order the not note by title, date, or color using order type ASC or DSC.
	data class Title(val order: OrderType): NoteOrder(order)
	data class Date(val order: OrderType): NoteOrder(order)
	data class Color(val order: OrderType): NoteOrder(order)

	// Order the note either by ASC or DSC
	sealed class OrderType {
		object ASC: OrderType()
		object DEC: OrderType()
	}

	// Update the orderType for NoteOrder
	fun update(orderType: OrderType): NoteOrder {
		return when(this) {
			is Color -> this.copy(order = orderType)
			is Date -> this.copy(order = orderType)
			is Title -> this.copy(order = orderType)
		}
	}
}