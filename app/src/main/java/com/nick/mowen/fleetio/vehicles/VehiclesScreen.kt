package com.nick.mowen.fleetio.vehicles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nick.mowen.fleetio.R
import com.nick.mowen.fleetio.components.VehicleListItem
import com.nick.mowen.fleetio.data.VehiclesResponse
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiclesScreen(vehiclesState: StateFlow<VehiclesResponse?>) {
    val vehicles = vehiclesState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) })
    }) { innerPadding ->
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
            VehicleListItem(it)
        }
    }
}

@Composable
@Preview
fun VehiclesScreenPreview() {
    //VehiclesScreen()
}