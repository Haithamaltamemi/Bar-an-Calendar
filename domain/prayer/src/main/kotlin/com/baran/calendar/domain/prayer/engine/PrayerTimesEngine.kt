package com.baran.calendar.domain.prayer.engine

import com.baran.calendar.domain.prayer.model.PrayerTime
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class PrayerTimesEngine(
    private val latitude: Double,
    private val longitude: Double,
    private val timezone: Double,
    private val altitude: Double = 0.0
) {
    
    companion object {
        private const val FAJR_ANGLE = 18.0
        private const val ISHA_ANGLE = 18.0
        private const val ASAR_FACTOR = 1.0
    }

    fun calculatePrayerTimes(year: Int, month: Int, day: Int): PrayerTime {
        val jd = julianDay(year, month, day)
        
        val fajr = getTime(jd, FAJR_ANGLE, true)
        val sunrise = getSunrise(jd)
        val dhuhr = getDhuhr(jd)
        val asr = getTime(jd, getAsrAngle(), false)
        val maghrib = getSunset(jd)
        val isha = getTime(jd, ISHA_ANGLE, false)
        
        return PrayerTime(
            fajr = formatTime(fajr),
            sunrise = formatTime(sunrise),
            dhuhr = formatTime(dhuhr),
            asr = formatTime(asr),
            maghrib = formatTime(maghrib),
            isha = formatTime(isha),
            imsak = formatTime(fajr - 10.0 / 60.0),
            midnight = formatTime((sunset(jd) + sunrise(jd)) / 2.0)
        )
    }

    private fun getTime(jd: Double, angle: Double, isFajr: Boolean): Double {
        val dec = sunDeclination(jd)
        val eqt = equationOfTime(jd)
        
        val numerator = -sin(Math.toRadians(angle)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dec))
        val denominator = cos(Math.toRadians(latitude)) * cos(Math.toRadians(dec))
        
        val hourAngle = if (isFajr) {
            -acos(numerator / denominator)
        } else {
            acos(numerator / denominator)
        }
        
        val time = 12.0 - Math.toDegrees(hourAngle) / 15.0 + timezone - longitude / 15.0 + eqt / 60.0
        return time
    }

    private fun getSunrise(jd: Double): Double = sunrise(jd)
    
    private fun getSunset(jd: Double): Double = sunset(jd)
    
    private fun getDhuhr(jd: Double): Double {
        val eqt = equationOfTime(jd)
        return 12.0 + timezone - longitude / 15.0 + eqt / 60.0
    }

    private fun sunrise(jd: Double): Double {
        val dec = sunDeclination(jd)
        val eqt = equationOfTime(jd)
        
        val numerator = -sin(Math.toRadians(0.833)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dec))
        val denominator = cos(Math.toRadians(latitude)) * cos(Math.toRadians(dec))
        
        val hourAngle = -acos(numerator / denominator)
        return 12.0 - Math.toDegrees(hourAngle) / 15.0 + timezone - longitude / 15.0 + eqt / 60.0
    }

    private fun sunset(jd: Double): Double {
        val dec = sunDeclination(jd)
        val eqt = equationOfTime(jd)
        
        val numerator = -sin(Math.toRadians(0.833)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dec))
        val denominator = cos(Math.toRadians(latitude)) * cos(Math.toRadians(dec))
        
        val hourAngle = acos(numerator / denominator)
        return 12.0 - Math.toDegrees(hourAngle) / 15.0 + timezone - longitude / 15.0 + eqt / 60.0
    }

    private fun sunDeclination(jd: Double): Double {
        val t = (jd - 2451545.0) / 36525.0
        val l = 280.46646 + t * (36000.76983 + t * 0.0003032)
        val m = 357.52911 + t * (35999.05029 - t * 0.0001536)
        
        val mRad = Math.toRadians(m)
        val e = 0.016708634 - t * (0.000042037 + t * 0.0000001267)
        
        val c = (1.914602 - t * (0.004817 + t * 0.000014)) * sin(mRad) +
                (0.019993 - t * 0.000101) * sin(2 * mRad) +
                0.000289 * sin(3 * mRad)
        
        val theta = l + c
        val lambda = theta - 0.00569 - 0.00478 * sin(Math.toRadians(125.04 - 1934.136 * t))
        
        val omega = 125.04 - 1934.136 * t
        val epsilon = 23.439291 - 0.0130042 * t - 0.00000164 * t * t + 0.000000504 * t * t * t +
                      0.00256 * cos(Math.toRadians(omega))
        
        return Math.toDegrees(asin(sin(Math.toRadians(epsilon)) * sin(Math.toRadians(lambda))))
    }

    private fun equationOfTime(jd: Double): Double {
        val t = (jd - 2451545.0) / 36525.0
        val l = 280.46646 + t * (36000.76983 + t * 0.0003032)
        val m = 357.52911 + t * (35999.05029 - t * 0.0001536)
        
        val mRad = Math.toRadians(m)
        val e = 0.016708634 - t * (0.000042037 + t * 0.0000001267)
        
        val c = (1.914602 - t * (0.004817 + t * 0.000014)) * sin(mRad) +
                (0.019993 - t * 0.000101) * sin(2 * mRad) +
                0.000289 * sin(3 * mRad)
        
        val theta = l + c
        val lambda = theta - 0.00569
        
        val omega = 125.04 - 1934.136 * t
        val epsilon = 23.439291 - 0.0130042 * t
        
        val y = tan(Math.toRadians(epsilon / 2.0))
        y * y
        
        val eq = 4.0 * (Math.toDegrees(atan2(y * y * sin(2.0 * Math.toRadians(l)), cos(2.0 * Math.toRadians(l)))) -
                atan2(e * e * sin(2.0 * Math.toRadians(m)), 1.0))
        
        return eq * 60.0
    }

    private fun getAsrAngle(): Double {
        return Math.toDegrees(atan(1.0 / (ASAR_FACTOR + tan(Math.toRadians(latitude) - Math.toRadians(sunDeclination(julianDay(2000, 1, 1))))))))
    }

    private fun julianDay(year: Int, month: Int, day: Int): Double {
        val a = (14 - month) / 12
        val y = year + 4800 - a
        val m = month + 12 * a - 3
        return day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045.5
    }

    private fun formatTime(time: Double): String {
        var t = time
        if (t < 0) t += 24.0
        if (t > 24.0) t -= 24.0
        
        val hours = t.toInt()
        val minutes = ((t - hours) * 60).toInt()
        return String.format("%02d:%02d", hours, minutes)
    }
}
