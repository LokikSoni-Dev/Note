package com.example.note.feature.note.presentation.note.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Composable to show Radio button with text.
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
@Composable
fun NoteRadioButton(
	modifier: Modifier = Modifier,
	text: String,
	selected: Boolean,
	onSelect: () -> Unit,
) {
	Row(
		modifier = modifier
			.clickable { onSelect() },
		verticalAlignment = Alignment.CenterVertically
	) {
		RadioButton(
			selected = selected,
			onClick = onSelect,
			colors = RadioButtonDefaults.colors(
				selectedColor = MaterialTheme.colors.primary,
				unselectedColor = MaterialTheme.colors.onBackground
			)
		)
		Text(text = text, style = MaterialTheme.typography.body1)
	}
}