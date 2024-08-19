package com.sw.wordgarden.domain.entity.alarm

import java.sql.Date

data class AlarmEntity (
    val qTitle: String?,
    val sqId: String?,
    val creator: String?,
    val date: Date?
)