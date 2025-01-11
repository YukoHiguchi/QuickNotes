package com.example.quicknotes.screens

sealed class Screen(val route: String) {
    object HomeScreen: Screen(route = "home")
    object AddNoteScreen: Screen(route = "add")
    object EditNoteScreen: Screen(route = "edit/")
}