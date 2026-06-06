package com.baran.calendar.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.baran.calendar.core.database.entity.UserEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserEventDao {
    @Insert
    suspend fun insert(event: UserEventEntity): Long

    @Update
    suspend fun update(event: UserEventEntity)

    @Delete
    suspend fun delete(event: UserEventEntity)

    @Query("SELECT * FROM user_events ORDER BY createdAt DESC")
    fun getAllEvents(): Flow<List<UserEventEntity>>

    @Query("SELECT * FROM user_events WHERE gregorianDate = :date")
    fun getEventsByGregorianDate(date: String): Flow<List<UserEventEntity>>

    @Query("SELECT * FROM user_events WHERE hijriDate = :date")
    fun getEventsByHijriDate(date: String): Flow<List<UserEventEntity>>

    @Query("SELECT * FROM user_events WHERE type = :type")
    fun getEventsByType(type: String): Flow<List<UserEventEntity>>

    @Query("SELECT * FROM user_events WHERE id = :id")
    suspend fun getEventById(id: Long): UserEventEntity?

    @Query("DELETE FROM user_events WHERE id = :id")
    suspend fun deleteEventById(id: Long)
}
