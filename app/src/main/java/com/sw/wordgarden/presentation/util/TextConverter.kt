package com.sw.wordgarden.presentation.util

import java.text.SimpleDateFormat
import java.util.Locale

object TextConverter {
    fun formatDateString(inputDateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val date = inputFormat.parse(inputDateString)

        return outputFormat.format(date!!)
    }
}