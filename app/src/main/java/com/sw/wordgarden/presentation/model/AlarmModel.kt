package com.sw.wordgarden.presentation.model

import java.sql.Date

data class AlarmModel (
    val quizId: String,
    val creator: String,
    val date: Date
)