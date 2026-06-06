package com.baran.calendar.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_events")
data class UserEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val gregorianDate: String,
    val hijriDate: String,
    val sabaeanDate: String,
    val type: String,
    val isAnnual: Boolean = false,
    val reminderMinutes: Int = 0,
    val notificationEnabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
