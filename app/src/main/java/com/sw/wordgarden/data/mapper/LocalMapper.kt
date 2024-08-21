package com.sw.wordgarden.data.mapper

import com.sw.wordgarden.data.dto.alarm.AlarmDto
import com.sw.wordgarden.domain.entity.alarm.AlarmEntity

object LocalMapper {
    //alarm
    fun AlarmEntity.toDto() = AlarmDto(
        qTitle = qTitle,
        sqId = sqId,
        creator = creator,
        date = date
    )

    fun AlarmDto.toEntity() = AlarmEntity(
        qTitle = qTitle,
        sqId = sqId,
        creator = creator,
        date = date
    )
}