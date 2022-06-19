package com.example.note.feature.note.presentation.addeditnote.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.note.feature.note.presentation.addeditnote.AddEditNoteScreen

/**
 * Composable to show transparent
 * text field in [AddEditNoteScreen].
 *
 * @author Lokik Soni
 * Created On 12-06-2022
 */
@Composable
fun TransparentTextField(
	modifier: Modifier = Modifier,
	text: String,
	hint: String,
	singleLine: Boolean = false,
	textStyle: TextStyle = TextStyle(),
	onValueChange: (String) -> Unit,
) {
	BasicTextField(
		modifier = modifier
			.fillMaxWidth(),
		value = text,
		singleLine = singleLine,
		textStyle = textStyle,
		onValueChange = onValueChange,
		decorationBox = { innerTextField ->
			Box {
				if (text.isEmpty()) Text(text = hint, style = textStyle, color = Color.DarkGray)
				innerTextField()
			}
		}
	)
}