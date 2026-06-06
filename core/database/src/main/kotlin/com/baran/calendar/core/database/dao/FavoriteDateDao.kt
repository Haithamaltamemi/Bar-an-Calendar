package com.baran.calendar.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.baran.calendar.core.database.entity.FavoriteDateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDateDao {
    @Insert
    suspend fun insert(date: FavoriteDateEntity): Long

    @Update
    suspend fun update(date: FavoriteDateEntity)

    @Delete
    suspend fun delete(date: FavoriteDateEntity)

    @Query("SELECT * FROM favorite_dates ORDER BY createdAt DESC")
    fun getAllFavoriteDates(): Flow<List<FavoriteDateEntity>>

    @Query("SELECT * FROM favorite_dates WHERE id = :id")
    suspend fun getFavoriteDateById(id: Long): FavoriteDateEntity?

    @Query("DELETE FROM favorite_dates WHERE id = :id")
    suspend fun deleteFavoriteDateById(id: Long)
}
