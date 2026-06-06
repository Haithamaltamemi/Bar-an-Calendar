package com.baran.calendar.domain.sabaean.converter

import com.baran.calendar.domain.sabaean.engine.SabaeanCalendarEngine
import com.baran.calendar.domain.sabaean.model.SabaeanDate

/**
 * Sabaean Converter Interface - ABSTRACTION LAYER
 * 
 * This interface provides the only way for external modules to interact
 * with the Sabaean calendar engine. All conversion requests must go through this interface.
 */
interface SabaeanConverter {
    fun julianDayToSabaean(jd: Double): SabaeanDate
    fun sabaeanToJulianDay(date: SabaeanDate): Double
    fun getMonthName(monthNumber: Int): String
    fun getDaysInMonth(month: Int, year: Int): Int
    fun isValidDate(year: Int, month: Int, day: Int): Boolean
}

/**
 * Default implementation of SabaeanConverter using the isolated SabaeanCalendarEngine
 */
class SabaeanConverterImpl(
    private val engine: SabaeanCalendarEngine = SabaeanCalendarEngine()
) : SabaeanConverter {
    
    override fun julianDayToSabaean(jd: Double): SabaeanDate {
        return engine.fromJulianDay(jd)
    }

    override fun sabaeanToJulianDay(date: SabaeanDate): Double {
        return engine.toJulianDay(date)
    }

    override fun getMonthName(monthNumber: Int): String {
        return engine.getMonthName(monthNumber)
    }

    override fun getDaysInMonth(month: Int, year: Int): Int {
        return engine.getDaysInMonth(month, year)
    }

    override fun isValidDate(year: Int, month: Int, day: Int): Boolean {
        return engine.isValidDate(year, month, day)
    }
}
