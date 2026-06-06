package com.baran.calendar.domain.calendar.model

data class GregorianDate(
    val year: Int,
    val month: Int,
    val day: Int,
    val dayOfWeek: Int = 0
) {
    val monthName: String
        get() = MONTH_NAMES_ARABIC[month - 1]
    
    val dayName: String
        get() = DAY_NAMES_ARABIC[dayOfWeek]

    companion object {
        private val MONTH_NAMES_ARABIC = listOf(
            "يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو",
            "يوليو", "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر"
        )
        
        private val DAY_NAMES_ARABIC = listOf(
            "الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت"
        )
    }
}
