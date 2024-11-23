package com.nick.mowen.fleetio.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nick.mowen.fleetio.R
import com.nick.mowen.fleetio.components.LazyLoadingColumn
import com.nick.mowen.fleetio.components.VehicleListItem
import com.nick.mowen.fleetio.data.Vehicle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiclesScreen(
    vehiclesState: StateFlow<List<Vehicle>?>,
    isLoadingState: StateFlow<Boolean>,
    canLoadMoreState: StateFlow<Boolean>,
    onLoadMore: () -> Unit,
    onSelectVehicle: (Vehicle) -> Unit,
) {
    val vehicles = vehiclesState.collectAsState()
    val isLoading = isLoadingState.collectAsState()
    val canLoadMore = canLoadMoreState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) })
    }) { innerPadding ->
        LazyLoadingColumn(
            modifier = Modifier.padding(innerPadding),
            loading = isLoading.value,
            listItems = vehicles.value ?: emptyList(),
            itemKey = { vehicle -> vehicle.id },
            itemContent = { vehicle -> VehicleListItem(vehicle, { onSelectVehicle(vehicle) }) },
            loadingItem = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            },
            canLoadMore = canLoadMore.value,
            loadMore = onLoadMore,
        )
    }
}

@Composable
@Preview
private fun VehiclesScreenPreview() {
    val data = MutableStateFlow(
        listOf(
            Vehicle(123456, 987, "123456", "", "Test Vehicle", "Car", "Active", "Cool", "Cars", 2024, "Operator", null, "", "", "", "")
        )
    ).asStateFlow()
    val isLoadingState = MutableStateFlow(false).asStateFlow()
    val canLoadMoreState = MutableStateFlow(false).asStateFlow()
    VehiclesScreen(data, isLoadingState, canLoadMoreState, onLoadMore = {}) {

    }
}