package com.nick.mowen.fleetio.vehicles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun VehiclesScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(
            "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
@Preview
fun VehiclesScreenPreview() {
    VehiclesScreen()
}