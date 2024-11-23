package com.nick.mowen.fleetio.data

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import com.nick.mowen.fleetio.ui.theme.Active
import com.nick.mowen.fleetio.ui.theme.InShop
import com.nick.mowen.fleetio.ui.theme.Inactive
import com.nick.mowen.fleetio.ui.theme.OutOfService
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


@Serializable
data class Vehicle(

    @SerializedName("id")
    val id: Long,

    @SerializedName("account_id")
    val accountId: Long,

    @SerializedName("vin")
    val vin: String,

    @SerializedName("license_plate")
    val licensePlate: String?,

    @SerializedName("name")
    val name: String,

    @SerializedName("vehicle_type_name")
    val vehicleType: String,

    @SerializedName("vehicle_status_name")
    val vehicleStatus: String,

    @SerializedName("make")
    val make: String?,

    @SerializedName("model")
    val model: String?,

    @SerializedName("year")
    val year: Int,

    @SerializedName("group_name")
    val group: String?,

    @SerializedName("default_image_url_small")
    val imageUrl: String?,

    // primary meter

    @SerializedName("primary_meter_unit")
    val primaryMeterUnit: String?,

    @SerializedName("primary_meter_value")
    val primaryMeterValue: String?,

    // secondary meter

    @SerializedName("secondary_meter_unit")
    val secondaryMeterUnit: String?,

    @SerializedName("secondary_meter_value")
    val secondaryMeterValue: String?,
) {

    fun getDescription() = "${group ?: "No Group"} • $vehicleStatus"

    fun getInitials() = "${make?.substring(0, 1) ?: ""}${model?.substring(0, 1) ?: ""}"

    fun getPrimaryMeter() = "$primaryMeterValue $primaryMeterUnit"

    fun hasSecondaryMeter() = secondaryMeterUnit != null

    fun getSecondaryMeter() = "$secondaryMeterValue $secondaryMeterUnit"

    fun hasLicensePlate() = licensePlate != null

    fun hasImage() = imageUrl != null

    fun getStatusColor() = when (vehicleStatus) {
        "Active" -> Active
        "In Shop" -> InShop
        "Inactive" -> Inactive
        "Out of Service" -> OutOfService
        else -> Color.Gray
    }
}