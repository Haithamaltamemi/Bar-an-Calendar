package com.baran.calendar.core.common

import java.util.Calendar

fun Double.toJulianDay(): Double = this

fun Int.toJulianDay(): Double = this.toDouble()

fun Calendar.toJulianDay(): Double {
    val a = (14 - (this[Calendar.MONTH] + 1)) / 12
    val y = this[Calendar.YEAR] + 4800 - a
    val m = (this[Calendar.MONTH] + 1) + 12 * a - 3
    return this[Calendar.DAY_OF_MONTH] + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045 + 0.5
}

fun Double.roundToTwoDecimals(): Double = kotlin.math.round(this * 100) / 100

fun Double.normalizeAngle(): Double {
    var angle = this
    while (angle < 0) angle += 360
    while (angle > 360) angle -= 360
    return angle
}
