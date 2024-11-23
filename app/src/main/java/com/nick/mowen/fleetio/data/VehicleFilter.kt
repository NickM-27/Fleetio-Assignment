package com.nick.mowen.fleetio.data

data class VehicleFilter(
    var statusFilter: Set<VehicleStatus> = VehicleStatus.entries.toMutableSet(),
    var nameFilter: String = ""
)