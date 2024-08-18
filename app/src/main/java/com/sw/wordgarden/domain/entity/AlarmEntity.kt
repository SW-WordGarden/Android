package com.sw.wordgarden.domain.entity

import java.sql.Date

data class AlarmEntity (
    val quizId: String,
    val creator: String,
    val date: Date
)