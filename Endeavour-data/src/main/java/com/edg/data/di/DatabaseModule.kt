package com.edg.data.di

import android.app.Application
import androidx.room.Room
import com.edg.data.local.Converters
import com.edg.data.local.FavProductDao
import com.edg.data.local.FavProductDatabase
import com.edg.data.util.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideFavouriteProductDatabase(app: Application): FavProductDatabase {
        return Room.databaseBuilder(app, FavProductDatabase::class.java, "word_db")
            .addTypeConverter(Converters(GsonParser(Gson()))).build()
    }

    @Singleton
    @Provides
    fun provideFavouriteProductDao(database: FavProductDatabase): FavProductDao {
        return database.dao
    }

}