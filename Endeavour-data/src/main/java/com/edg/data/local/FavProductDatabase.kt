package com.edg.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edg.data.local.entity.FavProductEntity


@Database(
    entities = [FavProductEntity::class],
    version = 1

)
abstract class FavProductDatabase : RoomDatabase() {
    abstract val dao: FavProductDatabase
}