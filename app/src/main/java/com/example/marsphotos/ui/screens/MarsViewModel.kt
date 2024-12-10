/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.data.FlickrPhotosRepository
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.model.FlickrPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface FlickrUiState {
    data class Success(val photos: List<FlickrPhoto>) : FlickrUiState
    object Error : FlickrUiState
    object Loading : FlickrUiState
}

class MarsViewModel(private val flickrPhotosRepository: FlickrPhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var flickrUiState: FlickrUiState by mutableStateOf(FlickrUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos("nature")
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getMarsPhotos(searchText: String) {
        viewModelScope.launch {
            flickrUiState = FlickrUiState.Loading
            flickrUiState = try {
                val response = flickrPhotosRepository.getPhotos(searchText)
                FlickrUiState.Success(response.photos.photo)
            } catch (e: IOException) {
                FlickrUiState.Error
            } catch (e: HttpException) {
                FlickrUiState.Error
            }
        }
    }

    /**
     * Factory for [MarsViewModel] that takes [MarsPhotosRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val flickrPhotosRepository = application.container.flickrPhotosRepository
                MarsViewModel(flickrPhotosRepository = flickrPhotosRepository)
            }
        }
    }
}
