package com.abdoali.asteroidradarcompose.datalayer.repository


import android.util.Log
import com.abdoali.asteroidradarcompose.asDatabaseModel
import com.abdoali.asteroidradarcompose.datalayer.repository.api.ApiService
import com.abdoali.asteroidradarcompose.datalayer.repository.api.parseAsteroidsJsonResult
import com.abdoali.asteroidradarcompose.datalayer.repository.database.Asteroids
import com.abdoali.asteroidradarcompose.datalayer.repository.database.DatabaseDao
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Named


interface Repos {

    fun getTodayAster(): Flow<List<Asteroids>>
    fun get7DayAster(): Flow<List<Asteroids>>
    fun getSavedAster(): Flow<List<Asteroids>>
    suspend fun updateDatabase()

}

class ReposImp @Inject constructor(
    private val apiService: ApiService,
    @Named("Today") val today: String,
    private val dao: DatabaseDao
) : Repos {


    override suspend fun updateDatabase() {
        try {
            val respo = apiService.getProperties(startDate = today)
            val jSONObjects = JSONObject(respo)
            val data = parseAsteroidsJsonResult(jSONObjects).asDatabaseModel()
            data.forEach {
                dao.insertAll(it)
            }
        } catch (
            e: Exception
        ) {
            Log.i("abdoali", e.toString())
        }
    }

    override fun getTodayAster(): Flow<List<Asteroids>> {
        return dao.getTodayAsterid(today)
    }

    override fun get7DayAster(): Flow<List<Asteroids>> {
        return dao.get7daysAsteroid(today)
    }

    override fun getSavedAster(): Flow<List<Asteroids>> {
        return dao.getSaveAsteroid(today)
    }


}