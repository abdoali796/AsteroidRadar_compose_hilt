package com.abdoali.asteroidradarcompose.datalayer.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg: Asteroids)

    //7day query
    @Query("SELECT * from asteroid_table WHERE closeApproachDate <:today ORDER BY closeApproachDate ")
    fun getSaveAsteroid(today: String): Flow<List<Asteroids>>

    //today query
    @Query("SELECT * from asteroid_table WHERE closeApproachDate = :today ORDER BY closeApproachDate ")
    fun getTodayAsterid(today: String): Flow<List<Asteroids>>

    @Query("SELECT * from asteroid_table WHERE closeApproachDate >= :today ORDER BY closeApproachDate ")
    fun get7daysAsteroid(today: String): Flow<List<Asteroids>>


    @Query("SELECT * from asteroid_table WHERE id = :asteroidsId ")
    fun getAster(asteroidsId: Long): Flow<Asteroids>

    //delete database
    @Query("DELETE  from asteroid_table")
    suspend fun clear()

}