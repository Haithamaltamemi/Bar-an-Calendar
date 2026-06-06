package com.baran.calendar.domain.calendar.engine

import com.baran.calendar.domain.calendar.model.HijriDate
import kotlin.math.floor

class HijriCalendarEngine {
    
    companion object {
        private const val ISLAMIC_EPOCH = 1948439.5
    }

    fun getCurrentHijriDate(): HijriDate {
        val jd = gregorianToJulianDay()
        return fromJulianDay(jd)
    }

    fun toJulianDay(date: HijriDate): Double {
        return (date.day + 29 * (date.month - 1) + 0.5 * (date.month - 1) + 
                354 * (date.year - 1) + floor((3 + 11 * date.year) / 30.0) + 
                ISLAMIC_EPOCH - 1)
    }

    fun fromJulianDay(jd: Double): HijriDate {
        val n = (jd - ISLAMIC_EPOCH).toLong()
        val q = n / 10631
        val r = n % 10631
        
        val a = (r * 30 + 10646) / 10646
        val w = ((a - 1) * 30) + ((r % 30 * 11 + 3) / 30)
        
        val year = (30 * q + w / 354 + 1).toInt()
        val month = (((w % 354) * 12 + 373) / 354).toInt()
        val day = ((r % 30) + 1).toInt()
        
        return HijriDate(year, month, day)
    }

    fun isLeapYear(year: Int): Boolean {
        return (11 * year + 3) % 30 < 11
    }

    fun getDaysInMonth(month: Int, year: Int): Int {
        return if (month == 12 && isLeapYear(year)) 30 else if (month % 2 == 1) 30 else 29
    }

    fun getMonthsInYear(): Int = 12

    private fun gregorianToJulianDay(): Double {
        val calendar = java.util.Calendar.getInstance()
        val year = calendar.get(java.util.Calendar.YEAR)
        val month = calendar.get(java.util.Calendar.MONTH) + 1
        val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)
        
        val a = (14 - month) / 12
        val y = year + 4800 - a
        val m = month + 12 * a - 3
        return day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045 + 0.5
    }
}
