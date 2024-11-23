package com.nick.mowen.fleetio.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CommentResponse(
    @SerializedName("start_cursor")
    val startCursor: String,

    @SerializedName("next_cursor")
    val next_cursor: String? = null,

    @SerializedName("records")
    val comments: List<VehicleComment>,
)

@Serializable
data class VehicleComment(
    @SerializedName("id")
    val id: Long,

    @SerializedName("comment")
    val comment: String,

    @SerializedName("created_at")
    val timestamp: String,

    @SerializedName("author")
    val author: CommentAuthor,
)

@Serializable
data class CommentAuthor(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,
)