package com.example.marsphotos.model

@kotlinx.serialization.Serializable
data class FlickrResponse(
    val photos: Photos
)

@kotlinx.serialization.Serializable
data class Photos(
    val photo: List<FlickrPhoto>
)

@kotlinx.serialization.Serializable
data class FlickrPhoto(
    val id: String,
    val title: String,
    val url_s: String
)
