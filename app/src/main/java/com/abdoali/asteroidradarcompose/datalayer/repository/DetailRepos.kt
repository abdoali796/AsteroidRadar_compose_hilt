package com.abdoali.asteroidradarcompose.datalayer.repository

import com.abdoali.asteroidradarcompose.datalayer.repository.database.Asteroids
import com.abdoali.asteroidradarcompose.datalayer.repository.database.DatabaseDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DetailRepos {
    fun getAster(id: Long): Flow<Asteroids>
}

class DetailReposImp @Inject constructor(
    private val dao: DatabaseDao
) : DetailRepos {
    override fun getAster(id: Long): Flow<Asteroids> {
        return dao.getAster(id)
    }

}