package com.sw.wordgarden.data.dto

import java.sql.Date

data class AlarmDto (
    val quizId: String,
    val creator: String,
    val date: Date
)