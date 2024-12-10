package com.example.marsphotos.data


import com.example.marsphotos.model.FlickrPhoto

import com.example.marsphotos.model.FlickrResponse
import com.example.marsphotos.network.FlickrAPIService

class FlickrPhotosRepository(private val flickrApiService: FlickrAPIService) {
    suspend fun getPhotos(searchText: String): FlickrResponse {
        return flickrApiService.searchPhotos(
            apiKey = "2da81cd23b04bbe04835e4fbf96cd641",
            searchText = searchText
        )
    }
}
