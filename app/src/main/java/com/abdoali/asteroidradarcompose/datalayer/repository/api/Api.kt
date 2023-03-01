package com.abdoali.asteroidradarcompose.datalayer.repository.api

import com.abdoali.asteroidradarcompose.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("planetary/apod")
    suspend fun getPicture(
        @Query("api_key") apiKey: String = KEY
    ): PictureOfDay


    @GET("neo/rest/v1/feed")
    suspend fun getProperties(
        @Query("api_key") apiKey: String = KEY,
        @Query("start_date") startDate: String,

        ): String
}


private const val KEY = "use your key"