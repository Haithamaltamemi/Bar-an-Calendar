package com.baran.calendar.core.data.repository

import com.baran.calendar.core.database.dao.FavoriteDateDao
import com.baran.calendar.core.database.entity.FavoriteDateEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteDateRepository @Inject constructor(
    private val favoriteDateDao: FavoriteDateDao
) {
    fun getAllFavoriteDates(): Flow<List<FavoriteDateEntity>> = favoriteDateDao.getAllFavoriteDates()

    suspend fun insertFavoriteDate(date: FavoriteDateEntity): Long = favoriteDateDao.insert(date)

    suspend fun updateFavoriteDate(date: FavoriteDateEntity) = favoriteDateDao.update(date)

    suspend fun deleteFavoriteDate(date: FavoriteDateEntity) = favoriteDateDao.delete(date)

    suspend fun deleteFavoriteDateById(id: Long) = favoriteDateDao.deleteFavoriteDateById(id)

    suspend fun getFavoriteDateById(id: Long): FavoriteDateEntity? = favoriteDateDao.getFavoriteDateById(id)
}
