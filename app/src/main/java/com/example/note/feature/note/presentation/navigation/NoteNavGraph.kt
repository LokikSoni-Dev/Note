package com.example.note.feature.note.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.note.feature.note.presentation.addeditnote.AddEditNoteScreen
import com.example.note.feature.note.presentation.addeditnote.AddEditNoteViewModel
import com.example.note.feature.note.presentation.note.NoteViewModel
import com.example.note.feature.note.presentation.note.NotesScreen

/**
 * Contain the routes for navigation between screens.
 */
sealed class Screen(val route: String) {
	object NotesScreen: Screen("notes_screen")
	object AddEditNote: Screen("add_edit_note")
}

/**
 * [NavHost] is a container to navigate between screens via [NavHostController].
 *
 * [NavHostController] keeps the track of the backStack and state of each screen.
 * Creating the [NavHostController] here so that all composable that need
 * it have access to it.
 *
 * [startDestination] in [NavHost] decide which screen to host first.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
@Composable
fun NoteNavGraph(
	navHostController: NavHostController = rememberNavController(),
	startDestination: String = Screen.NotesScreen.route
) {
	NavHost(
		navController = navHostController,
		startDestination = startDestination
	) {
		composable(
			route = Screen.NotesScreen.route
		) {
			val noteViewModel: NoteViewModel = hiltViewModel()
			NotesScreen(
				navController = navHostController,
				viewModel = noteViewModel
			)
		}
		composable(
			route = Screen.AddEditNote.route + "?noteId={noteId}&colorId={colorId}",
			arguments = listOf(
				navArgument(name = "noteId") {
					type = NavType.IntType
					defaultValue = -1
				},
				navArgument(name = "colorId") {
					type = NavType.IntType
					defaultValue = -1
				}
			)
		) { backStackEntry ->
			val addEditNoteViewModel: AddEditNoteViewModel = hiltViewModel()
			AddEditNoteScreen(
				navController = navHostController,
				viewModel = addEditNoteViewModel,
				noteColor = backStackEntry.arguments?.getInt("colorId") ?: -1
			)
		}
	}
}