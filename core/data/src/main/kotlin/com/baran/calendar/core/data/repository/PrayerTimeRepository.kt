package com.baran.calendar.core.data.repository

import com.baran.calendar.core.database.dao.PrayerTimeAdjustmentDao
import com.baran.calendar.core.database.entity.PrayerTimeAdjustmentEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrayerTimeRepository @Inject constructor(
    private val prayerTimeAdjustmentDao: PrayerTimeAdjustmentDao
) {
    fun getAllAdjustments(): Flow<List<PrayerTimeAdjustmentEntity>> = prayerTimeAdjustmentDao.getAllAdjustments()

    suspend fun getAdjustmentByPrayerName(prayerName: String): PrayerTimeAdjustmentEntity? = 
        prayerTimeAdjustmentDao.getAdjustmentByPrayerName(prayerName)

    suspend fun insertAdjustment(adjustment: PrayerTimeAdjustmentEntity): Long = prayerTimeAdjustmentDao.insert(adjustment)

    suspend fun updateAdjustment(adjustment: PrayerTimeAdjustmentEntity) = prayerTimeAdjustmentDao.update(adjustment)

    suspend fun deleteAdjustment(adjustment: PrayerTimeAdjustmentEntity) = prayerTimeAdjustmentDao.delete(adjustment)

    suspend fun deleteAllAdjustments() = prayerTimeAdjustmentDao.deleteAll()
}
