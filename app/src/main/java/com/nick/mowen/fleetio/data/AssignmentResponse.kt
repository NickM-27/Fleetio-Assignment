package com.nick.mowen.fleetio.data

import com.google.gson.annotations.SerializedName

data class AssignmentResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("contact_full_name")
    val name: String,

    @SerializedName("contact_image_url")
    val imageUrl: String?,

    @SerializedName("current")
    val current: Boolean,

    @SerializedName("future")
    val future: Boolean,
)