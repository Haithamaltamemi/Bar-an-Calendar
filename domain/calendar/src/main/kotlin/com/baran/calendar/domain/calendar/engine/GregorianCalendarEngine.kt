package com.baran.calendar.domain.calendar.engine

import com.baran.calendar.domain.calendar.model.GregorianDate
import kotlin.math.floor

class GregorianCalendarEngine {
    
    fun getCurrentGregorianDate(): GregorianDate {
        val calendar = java.util.Calendar.getInstance()
        return GregorianDate(
            year = calendar.get(java.util.Calendar.YEAR),
            month = calendar.get(java.util.Calendar.MONTH) + 1,
            day = calendar.get(java.util.Calendar.DAY_OF_MONTH),
            dayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK) - 1
        )
    }

    fun toJulianDay(date: GregorianDate): Double {
        val a = (14 - date.month) / 12
        val y = date.year + 4800 - a
        val m = date.month + 12 * a - 3
        return date.day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045 + 0.5
    }

    fun fromJulianDay(jd: Double): GregorianDate {
        val z = floor(jd + 0.5).toLong()
        val a = if (z < 2299161) z else {
            val alpha = floor((z - 1867216.25) / 36524.25).toLong()
            z + 1 + alpha - alpha / 4
        }
        
        val b = a + 1524
        val c = floor((b - 122.1) / 365.25).toLong()
        val d = floor(365.25 * c).toLong()
        val e = floor((b - d) / 30.6001).toLong()

        val day = (b - d - floor(30.6001 * e).toLong()).toInt()
        val month = if (e < 14) (e - 1).toInt() else (e - 13).toInt()
        val year = (if (month > 2) c - 4716 else c - 4715).toInt()

        return GregorianDate(year, month, day)
    }

    fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    fun getDaysInMonth(month: Int, year: Int): Int {
        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (isLeapYear(year)) 29 else 28
            else -> 0
        }
    }

    fun getMonthsInYear(): Int = 12
}
