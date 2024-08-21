package com.sw.wordgarden.data.dto.alarm

import java.sql.Date

data class AlarmDto (
    val qTitle: String?,
    val sqId: String?,
    val creator: String?,
    val date: Date?
)