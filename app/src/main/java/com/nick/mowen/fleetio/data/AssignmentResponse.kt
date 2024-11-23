package com.nick.mowen.fleetio.data

import com.google.gson.annotations.SerializedName

data class AssignmentResponse(

    @SerializedName("start_cursor")
    val startCursor: String,

    @SerializedName("next_cursor")
    val nextCursor: String?,

    @SerializedName("records")
    val assignments: List<Assignment>
)

data class Assignment(

    @SerializedName("id")
    val id: String,

    @SerializedName("current")
    val current: Boolean,

    @SerializedName("future")
    val future: Boolean,

    @SerializedName("contact")
    val contact: Contact
)

data class Contact(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("default_image_url")
    val imageUrl: String,
)