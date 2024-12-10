package com.example.marsphotos.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlickrResponse(
    val photos: Photos
)

@Serializable
data class Photos(
    val photo: List<FlickrPhoto>
)

@Serializable
data class FlickrPhoto(
    val id: String,
    val title: String,
    @SerialName("url_s") val imageUrl: String
)
