package com.example.quicknotes.scaffold

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FabButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        shape = CircleShape
    ) {
        Icon(Icons.Filled.Add, "Floating action button")
    }
}

@Preview
@Composable
private fun FabButtonPreview() {
    var onClick: () -> Unit
    FabButton() {
        onClick = {}
    }
    
}