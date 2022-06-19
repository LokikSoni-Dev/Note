package com.example.note.feature.note.presentation.note.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.note.R
import com.example.note.feature.note.domain.util.NoteOrder
import com.example.note.feature.note.presentation.note.NotesScreen

/**
 * Composable to show Order section using
 * [NoteRadioButton] in [NotesScreen].
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
@Composable
fun OrderSection(
	modifier: Modifier = Modifier,
	noteOrder: NoteOrder = NoteOrder.Date(NoteOrder.OrderType.DEC),
	onOrderChange: (NoteOrder) -> Unit,
) {
	Column(
		modifier = modifier
	) {
		Row(
			modifier = Modifier.fillMaxWidth()
		) {
			NoteRadioButton(
				text = stringResource(R.string.title),
				selected = noteOrder is NoteOrder.Title,
				onSelect = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) }
			)
			Spacer(modifier = Modifier.width(8.dp))
			NoteRadioButton(
				text = stringResource(R.string.date),
				selected = noteOrder is NoteOrder.Date,
				onSelect = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) }
			)
			Spacer(modifier = Modifier.width(8.dp))
			NoteRadioButton(
				text = stringResource(R.string.color),
				selected = noteOrder is NoteOrder.Color,
				onSelect = { onOrderChange(NoteOrder.Color(noteOrder.orderType)) }
			)
		}
		Spacer(modifier = Modifier.height(5.dp))
		Row(
			modifier = Modifier.fillMaxWidth()
		) {
			NoteRadioButton(
				text = stringResource(R.string.ascending),
				selected = noteOrder.orderType is NoteOrder.OrderType.ASC,
				onSelect = {
					onOrderChange((noteOrder.update(NoteOrder.OrderType.ASC)))
				}
			)
			Spacer(modifier = Modifier.width(8.dp))
			NoteRadioButton(
				text = stringResource(R.string.descending),
				selected = noteOrder.orderType is NoteOrder.OrderType.DEC,
				onSelect = { onOrderChange(noteOrder.update(NoteOrder.OrderType.DEC)) }
			)
		}
	}
}