package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class UserResourceDto(
    @SerializedName("coins") val coins:Int?,
    @SerializedName("wateringCans") val wateringCans:Int?,
    @SerializedName("plantCount") val plantCount:Int?,
)
