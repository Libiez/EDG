package com.edg.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.edg.data.local.entity.FavProductEntity


@Database(
    entities = [FavProductEntity::class],
    version = 1

)

@TypeConverters(Converters::class)
abstract class FavProductDatabase : RoomDatabase() {
    abstract val dao: FavProductDao
}