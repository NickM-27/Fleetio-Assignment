package com.nick.mowen.fleetio.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nick.mowen.fleetio.api.FleetioClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VehiclesViewModel : ViewModel() {

    private val client = FleetioClient()

    private val _vehicles: MutableStateFlow<VehiclesResponse?> = MutableStateFlow(null)
    val vehicles: StateFlow<VehiclesResponse?> = _vehicles.asStateFlow()

    fun getVehicles() = viewModelScope.launch {
        _vehicles.emit(client.getVehicles())
    }
}