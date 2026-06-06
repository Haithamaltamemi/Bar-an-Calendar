package com.baran.calendar.domain.sabaean.model

/**
 * Sabaean Date Model - Completely isolated module
 * This module defines the data model for the ancient Yemeni Sabaean calendar
 * All conversion rules and calculations must occur through well-defined interfaces
 */
data class SabaeanDate(
    val year: Int,
    val month: Int,
    val day: Int
) {
    val monthName: String
        get() = MONTH_NAMES[month - 1]

    companion object {
        private val MONTH_NAMES = listOf(
            "ذو دوان",
            "ذو الهلال", 
            "ذو المعين",
            "ذو الثبه",
            "ذو السراب",
            "ذو الملحة",
            "ذو القيظ",
            "ذو الخريف",
            "ذو المزان",
            "ذو العتمة",
            "ذو البرد",
            "ذو السكون"
        )
    }
}
