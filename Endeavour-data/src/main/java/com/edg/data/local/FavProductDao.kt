package com.edg.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edg.data.local.entity.FavProductEntity

@Dao
interface FavProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteProduct(product: FavProductEntity)

    @Query("DELETE FROM FavProductEntity WHERE id IN(:id)")
    suspend fun removeFavouriteProduct(id:String)

    @Query("SELECT * FROM FavProductEntity")
    suspend fun getFavouriteProducts():List<FavProductEntity>

}