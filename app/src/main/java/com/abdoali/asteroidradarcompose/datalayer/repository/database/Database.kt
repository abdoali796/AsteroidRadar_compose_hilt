package com.abdoali.asteroidradarcompose.datalayer.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Asteroids::class], version = 1 , exportSchema = false)
abstract class Database():RoomDatabase() {
    abstract fun asterDao(): DatabaseDao
}