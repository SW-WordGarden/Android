package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class ReportInfoDto(
    @SerializedName("reporterId") val reporterId: String?,
    @SerializedName("reportedId") val reportedId: String?,
)