package com.baran.calendar.domain.sabaean.engine

import com.baran.calendar.domain.sabaean.model.SabaeanDate
import kotlin.math.floor

/**
 * Sabaean Calendar Engine - ISOLATED MODULE
 * 
 * This engine contains all logic for the ancient Yemeni Sabaean calendar
 * including epoch definitions, conversion algorithms, and month calculations.
 * 
 * All external interaction must occur through well-defined interfaces.
 * No other module should directly access internal implementation details.
 * 
 * Future modifications (new historical research, alternative scholarly interpretations)
 * can be made here without affecting other application components.
 */
class SabaeanCalendarEngine {
    
    companion object {
        // Sabaean calendar epoch (year 0 = 950 BCE in Gregorian calendar)
        private const val SABAEAN_EPOCH_YEAR = 2786
        private const val SABAEAN_EPOCH_JD = 1948440.0
        
        // Calendar properties
        private const val MONTHS_PER_YEAR = 12
        private const val DAYS_PER_LUNAR_MONTH = 30  // Average
    }

    /**
     * Get current Sabaean date from Julian Day Number
     */
    fun fromJulianDay(jd: Double): SabaeanDate {
        // Days since Sabaean epoch
        val daysSinceEpoch = (jd - SABAEAN_EPOCH_JD).toLong()
        
        // Calculate year (using 354-day lunar year average)
        val year = (daysSinceEpoch / 354) + 1
        val dayInYear = (daysSinceEpoch % 354) + 1
        
        // Calculate month and day
        val month = ((dayInYear - 1) / 30) + 1
        val day = ((dayInYear - 1) % 30) + 1
        
        return SabaeanDate(
            year = year.toInt(),
            month = month.toInt().coerceIn(1, MONTHS_PER_YEAR),
            day = day.toInt().coerceIn(1, 30)
        )
    }

    /**
     * Convert Sabaean date to Julian Day Number
     */
    fun toJulianDay(date: SabaeanDate): Double {
        val dayInYear = (date.month - 1) * 30 + date.day
        val daysSinceEpoch = (date.year - 1) * 354 + dayInYear
        return SABAEAN_EPOCH_JD + daysSinceEpoch
    }

    /**
     * Get the display name for a Sabaean month
     */
    fun getMonthName(monthNumber: Int): String {
        return if (monthNumber in 1..MONTHS_PER_YEAR) {
            SABAEAN_MONTH_NAMES[monthNumber - 1]
        } else {
            ""
        }
    }

    /**
     * Check if a Sabaean month has 30 or 29 days
     */
    fun getDaysInMonth(month: Int, year: Int): Int {
        return if (month in 1..MONTHS_PER_YEAR) {
            if (isLeapMonth(month, year)) 30 else 29
        } else {
            0
        }
    }

    /**
     * Determine if a month is a leap month in the Sabaean calendar
     */
    private fun isLeapMonth(month: Int, year: Int): Boolean {
        // Sabaean leap month rules (can be adjusted based on historical research)
        return (month % 2 == 1) // Odd months have 30 days
    }

    /**
     * Get total number of months in a year
     */
    fun getMonthsInYear(): Int = MONTHS_PER_YEAR

    /**
     * Validate a Sabaean date
     */
    fun isValidDate(year: Int, month: Int, day: Int): Boolean {
        if (month !in 1..MONTHS_PER_YEAR) return false
        if (day !in 1..30) return false
        if (year < 1) return false
        return true
    }

    companion object {
        private val SABAEAN_MONTH_NAMES = listOf(
            "ذو دوان",       // Month 1
            "ذو الهلال",     // Month 2
            "ذو المعين",     // Month 3
            "ذو الثبه",      // Month 4
            "ذو السرا��",     // Month 5
            "ذو الملحة",     // Month 6
            "ذو القيظ",      // Month 7
            "ذو الخريف",     // Month 8
            "ذو المزان",     // Month 9
            "ذو العتمة",     // Month 10
            "ذو البرد",      // Month 11
            "ذو السكون"      // Month 12
        )
    }
}
