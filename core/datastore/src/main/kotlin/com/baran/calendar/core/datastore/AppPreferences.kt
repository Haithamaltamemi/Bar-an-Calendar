package com.baran.calendar.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "baran_preferences")

class AppPreferences(private val context: Context) {
    companion object {
        private val THEME_MODE = stringPreferencesKey("theme_mode")
        private val LANGUAGE = stringPreferencesKey("language")
        private val GREGORIAN_MONTH_SYSTEM = stringPreferencesKey("gregorian_month_system")
        private val PRAYER_CALCULATION_METHOD = stringPreferencesKey("prayer_calculation_method")
        private val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
        private val ENABLE_NOTIFICATIONS = booleanPreferencesKey("enable_notifications")
        private val ENABLE_PRAYER_REMINDERS = booleanPreferencesKey("enable_prayer_reminders")
    }

    val themeMode: Flow<String> = context.dataStore.data.map { it[THEME_MODE] ?: "system" }
    val language: Flow<String> = context.dataStore.data.map { it[LANGUAGE] ?: "ar" }
    val gregorianMonthSystem: Flow<String> = context.dataStore.data.map { it[GREGORIAN_MONTH_SYSTEM] ?: "modern" }
    val prayerCalculationMethod: Flow<String> = context.dataStore.data.map { it[PRAYER_CALCULATION_METHOD] ?: "UmmAlQura" }
    val isFirstLaunch: Flow<Boolean> = context.dataStore.data.map { it[IS_FIRST_LAUNCH] ?: true }
    val enableNotifications: Flow<Boolean> = context.dataStore.data.map { it[ENABLE_NOTIFICATIONS] ?: true }
    val enablePrayerReminders: Flow<Boolean> = context.dataStore.data.map { it[ENABLE_PRAYER_REMINDERS] ?: true }

    suspend fun setThemeMode(mode: String) {
        context.dataStore.edit { it[THEME_MODE] = mode }
    }

    suspend fun setLanguage(lang: String) {
        context.dataStore.edit { it[LANGUAGE] = lang }
    }

    suspend fun setGregorianMonthSystem(system: String) {
        context.dataStore.edit { it[GREGORIAN_MONTH_SYSTEM] = system }
    }

    suspend fun setPrayerCalculationMethod(method: String) {
        context.dataStore.edit { it[PRAYER_CALCULATION_METHOD] = method }
    }

    suspend fun setFirstLaunchComplete() {
        context.dataStore.edit { it[IS_FIRST_LAUNCH] = false }
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { it[ENABLE_NOTIFICATIONS] = enabled }
    }

    suspend fun setPrayerRemindersEnabled(enabled: Boolean) {
        context.dataStore.edit { it[ENABLE_PRAYER_REMINDERS] = enabled }
    }
}
