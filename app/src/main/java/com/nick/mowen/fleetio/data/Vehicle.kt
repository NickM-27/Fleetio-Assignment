package com.nick.mowen.fleetio.data

import com.google.gson.annotations.SerializedName

data class Vehicle(

    @SerializedName("id")
    val id: Long,

    @SerializedName("account_id")
    val accountId: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("vehicle_type_name")
    val vehicleType: String,

    @SerializedName("vehicle_status_name")
    val vehicleStatus: String,

    @SerializedName("make")
    val make: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("year")
    val year: Int,
)