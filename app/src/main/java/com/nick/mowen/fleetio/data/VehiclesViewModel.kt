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

    private val _vehicleResponses: MutableStateFlow<List<VehiclesResponse>?> = MutableStateFlow(null)

    private val _vehicles: MutableStateFlow<List<Vehicle>?> = MutableStateFlow(null)
    val vehicles: StateFlow<List<Vehicle>?> = _vehicles.asStateFlow()

    private val _assignmentResponses: MutableStateFlow<AssignmentResponse?> = MutableStateFlow(null)

    private val _assignment: MutableStateFlow<Assignment?> = MutableStateFlow(null)
    val assignment: StateFlow<Assignment?> = _assignment.asStateFlow()

    private val _commentResponses: MutableStateFlow<List<CommentResponse>?> = MutableStateFlow(null)

    private val _comments: MutableStateFlow<List<VehicleComment>?> = MutableStateFlow(null)
    val comments: StateFlow<List<VehicleComment>?> = _comments.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _canLoadMoreVehicles: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val canLoadMoreVehicles: StateFlow<Boolean> = _canLoadMoreVehicles.asStateFlow()

    fun getVehicles() = viewModelScope.launch {
        _isLoading.emit(true)
        client.getVehicles()?.let { vehiclesResponse ->
            _isLoading.emit(false)
            _vehicleResponses.emit(listOf(vehiclesResponse))
            _vehicles.emit(vehiclesResponse.vehicles)
            _canLoadMoreVehicles.emit(vehiclesResponse.next_cursor != null)
        }
    }

    fun getMoreVehicles() = viewModelScope.launch {
        if (!_canLoadMoreVehicles.value) {
            return@launch
        }

        _vehicleResponses.value?.lastOrNull()?.let { lastResponse ->
            _isLoading.emit(true)
            client.getVehicles(lastResponse.next_cursor)?.let { vehiclesResponse ->
                _isLoading.emit(false)
                val currentResponses = _vehicleResponses.value?.toMutableList() ?: mutableListOf()
                currentResponses.add(vehiclesResponse)
                _vehicleResponses.emit(currentResponses)

                val currentVehicles = _vehicles.value?.toMutableList() ?: mutableListOf()
                currentVehicles.addAll(vehiclesResponse.vehicles)
                _vehicles.emit(currentVehicles)

                _canLoadMoreVehicles.emit(vehiclesResponse.next_cursor != null)
            }
        }
    }

    fun getVehicleInfo(vehicleId: Long) = viewModelScope.launch {
        _isLoading.emit(true)
        client.getVehicleAssignment(vehicleId)?.let { assignmentResponse ->
            _assignmentResponses.emit(assignmentResponse)
            _assignment.emit(assignmentResponse.assignments.firstOrNull())
        }
        client.getCommentsOnVehicle(vehicleId)?.let { commentResponse ->
            _isLoading.emit(false)
            _commentResponses.emit(listOf(commentResponse))
            _comments.emit(commentResponse.comments)
        }
    }
}