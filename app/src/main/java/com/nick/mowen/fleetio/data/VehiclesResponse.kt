package com.nick.mowen.fleetio.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class VehiclesResponse(
    @SerializedName("start_cursor")
    val startCursor: String,

    @SerializedName("next_cursor")
    val next_cursor: String? = null,

    @SerializedName("records")
    val vehicles: List<Vehicle>,
)