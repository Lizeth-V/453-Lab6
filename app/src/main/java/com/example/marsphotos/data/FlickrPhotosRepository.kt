package com.example.marsphotos.data

import com.example.marsphotos.network.FlickrApiService
import com.example.marsphotos.model.FlickrPhoto

/**
 * Repository for fetching photos from Flickr API.
 */
class FlickrPhotosRepository(private val service: FlickrApiService) {
    suspend fun getPhotos(apiKey: String, searchText: String): List<FlickrPhoto> {
        return service.searchPhotos(apiKey, searchText).photos.photo
    }
}