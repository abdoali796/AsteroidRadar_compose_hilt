package com.abdoali.asteroidradarcompose.datalayer.repository

import com.abdoali.asteroidradarcompose.PictureOfDay
import com.abdoali.asteroidradarcompose.datalayer.repository.api.ApiService
import javax.inject.Inject

interface PicRepos {
    suspend fun getPic(): PictureOfDay
}

class PicReposImp @Inject constructor(
    private val apiService: ApiService
) : PicRepos {

    override suspend fun getPic(): PictureOfDay {
        return try {
            apiService.getPicture()
        } catch (e: Exception) {
            PictureOfDay("", "", "")
        }

    }

}