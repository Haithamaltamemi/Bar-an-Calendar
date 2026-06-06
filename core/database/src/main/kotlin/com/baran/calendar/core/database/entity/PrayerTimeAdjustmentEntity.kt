package com.baran.calendar.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prayer_adjustments")
data class PrayerTimeAdjustmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val prayerName: String,
    val adjustmentMinutes: Int = 0,
    val isEnabled: Boolean = true
)
