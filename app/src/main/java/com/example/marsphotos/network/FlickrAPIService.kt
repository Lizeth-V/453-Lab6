package com.example.marsphotos.network
import com.example.marsphotos.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPIService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun searchPhotos(
        @Query("api_key") apiKey: String = "2da81cd23b04bbe04835e4fbf96cd641",
        @Query("text") searchText: String,
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1
    ): FlickrResponse
}



