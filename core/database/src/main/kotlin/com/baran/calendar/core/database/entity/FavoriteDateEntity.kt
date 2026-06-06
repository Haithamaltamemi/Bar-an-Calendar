package com.baran.calendar.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_dates")
data class FavoriteDateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val gregorianDate: String,
    val hijriDate: String,
    val sabaeanDate: String,
    val label: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = true
)
