package com.example.marsphotos.network;

import com.example.marsphotos.model.FlickrResponse
import com.example.marsphotos.model.FlickrPhoto
import retrofit2.http.GET
import retrofit2.http.Query

public interface FlickrApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun searchPhotos(
            @Query("api_key") apiKey: String,
            @Query("text") searchText: String,
            @Query("per_page") perPage: Int = 20,
            @Query("page") page: Int = 1
    ): FlickrResponse
}
