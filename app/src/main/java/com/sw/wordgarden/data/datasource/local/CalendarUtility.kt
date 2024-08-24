package com.sw.wordgarden.data.datasource.local

import java.util.Calendar

object CalendarUtility {
    fun isSameDay(timestamp: Long): Boolean {
        if (timestamp == 0L) return false

        val calendar = Calendar.getInstance()
        val currentDay = calendar.get(Calendar.DAY_OF_YEAR)
        val currentYear = calendar.get(Calendar.YEAR)

        calendar.timeInMillis = timestamp
        val timestampDay = calendar.get(Calendar.DAY_OF_YEAR)
        val timestampYear = calendar.get(Calendar.YEAR)

        return currentDay == timestampDay && currentYear == timestampYear
    }
}