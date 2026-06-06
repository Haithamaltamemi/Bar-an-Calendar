package com.baran.calendar.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.baran.calendar.core.database.entity.PrayerTimeAdjustmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayerTimeAdjustmentDao {
    @Insert
    suspend fun insert(adjustment: PrayerTimeAdjustmentEntity): Long

    @Update
    suspend fun update(adjustment: PrayerTimeAdjustmentEntity)

    @Delete
    suspend fun delete(adjustment: PrayerTimeAdjustmentEntity)

    @Query("SELECT * FROM prayer_adjustments WHERE prayerName = :prayerName")
    suspend fun getAdjustmentByPrayerName(prayerName: String): PrayerTimeAdjustmentEntity?

    @Query("SELECT * FROM prayer_adjustments")
    fun getAllAdjustments(): Flow<List<PrayerTimeAdjustmentEntity>>

    @Query("DELETE FROM prayer_adjustments")
    suspend fun deleteAll()
}
