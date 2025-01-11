package com.example.quicknotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quicknotes.screens.HomeScreen
import com.example.quicknotes.screens.NoteScreen
import com.example.quicknotes.screens.Screen
import com.example.quicknotes.ui.theme.QuickNotesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickNotesTheme {
                QuickNoteApp()
            }
        }
    }
}

@Composable
fun QuickNoteApp() {
    val notes = remember { mutableStateListOf<Note>() }
    var noteIdCounter by remember { mutableIntStateOf(notes.size) }
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                notes,
                onAddNote = { navController.navigate(Screen.AddNoteScreen.route) },
                onEditNote = { note -> navController.navigate(Screen.EditNoteScreen.route + "${note.id}") })
        }
        composable(route = Screen.AddNoteScreen.route) {
            NoteScreen(
                save = { note ->
                    notes.add(note.copy(id = noteIdCounter++))
                    navController.popBackStack()
                 },
                cancel = { navController.popBackStack() },
                note = null
            )
        }
        composable(route = Screen.EditNoteScreen.route + "noteId") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toInt() ?: -1
            val note = notes.find { it.id == noteId }
            if (note != null) {
                NoteScreen(
                    note = note,
                    save = { updatedNote ->
                        note.title = updatedNote.title
                        note.content = updatedNote.content
                        navController.popBackStack()
                    },
                    cancel = { navController.popBackStack() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuickNotesPreview() {
    QuickNotesTheme {
        QuickNoteApp()
    }
}