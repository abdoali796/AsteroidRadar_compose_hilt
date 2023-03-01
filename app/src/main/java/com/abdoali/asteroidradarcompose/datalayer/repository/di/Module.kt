package com.abdoali.asteroidradarcompose.datalayer.repository.di

import android.content.Context
import androidx.room.Room
import com.abdoali.asteroidradarcompose.datalayer.repository.*
import com.abdoali.asteroidradarcompose.datalayer.repository.api.ApiService
import com.abdoali.asteroidradarcompose.datalayer.repository.api.Constants.BASE_URL
import com.abdoali.asteroidradarcompose.datalayer.repository.database.Database
import com.abdoali.asteroidradarcompose.datalayer.repository.database.DatabaseDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    @Named("Today")
    fun getToday(): String {
        val time = Calendar.getInstance().time
        val format = SimpleDateFormat("YYYY-MM-dd", Locale.US)
        return format.format(time)
    }

    @Provides
    @Singleton
    fun apiAstaer(): ApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS).build()
        val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .baseUrl(BASE_URL).build()
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun daoProvide(db: Database): DatabaseDao {
        return db.asterDao()
    }

    @Provides
    @Singleton
    fun databaseProvide(@ApplicationContext app: Context): Database {
        return Room.databaseBuilder(app, Database::class.java, "aster_db").build()
    }


    @Provides
    @Singleton
    fun providesReposImp(
        api: ApiService,
        dao: DatabaseDao,
        @Named("Today") today: String
    ): Repos {
        return ReposImp(api, today, dao)
    }

    @Provides
    @Singleton
    fun providesDetailRepos(dao: DatabaseDao): DetailRepos {
        return DetailReposImp(dao)
    }


    @Provides
    @Singleton
    fun providesPicRepos(api: ApiService): PicRepos {
        return PicReposImp(api)
    }
}