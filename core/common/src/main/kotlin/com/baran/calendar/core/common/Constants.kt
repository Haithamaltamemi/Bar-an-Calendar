package com.baran.calendar.core.common

object Constants {
    const val GREGORIAN_EPOCH_JD = 1721425.5
    const val HIJRI_EPOCH_JD = 1948439.5
    const val SABAEAN_EPOCH_YEAR = 2786
    const val SABAEAN_EPOCH_JD = 1948440.0

    object Prayer {
        const val FAJR = "Fajr"
        const val SUNRISE = "Sunrise"
        const val DHUHR = "Dhuhr"
        const val ASR = "Asr"
        const val MAGHRIB = "Maghrib"
        const val ISHA = "Isha"
    }

    object Islamic {
        val MONTHS = listOf(
            "محرم", "صفر", "ربيع الأول", "ربيع الثاني",
            "جمادى الأولى", "جمادى الآخرة", "رجب", "شعبان",
            "رمضان", "شوال", "ذو القعدة", "ذو الحجة"
        )
    }

    object Sabaean {
        val MONTHS = listOf(
            "ذو دوان", "ذو الهلال", "ذو المعين", "ذو الثبه",
            "ذو السراب", "ذو الملحة", "ذو القيظ", "ذو الخريف",
            "ذو المزان", "ذو العتمة", "ذو البرد", "ذو السكون"
        )
    }
}
