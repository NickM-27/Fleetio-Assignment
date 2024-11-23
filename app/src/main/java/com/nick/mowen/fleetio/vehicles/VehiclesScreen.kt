package com.nick.mowen.fleetio.vehicles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nick.mowen.fleetio.data.VehiclesResponse
import kotlinx.coroutines.flow.StateFlow

@Composable
fun VehiclesScreen(vehiclesState: StateFlow<VehiclesResponse?>) {
    val vehicles = vehiclesState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (vehicles.value == null) {
            CircularProgressIndicator()
        } else {
            VehiclesList(vehicles.value!!, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun VehiclesList(vehicles: VehiclesResponse, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        vehicles.vehicles.map {
            Text(it.make)
        }
    }
}

@Composable
@Preview
fun VehiclesScreenPreview() {
    //VehiclesScreen()
}