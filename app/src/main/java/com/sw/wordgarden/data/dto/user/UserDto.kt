package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("uRank") val uRank: Int?,
    @SerializedName("uPoint") val uPoint: Int?,
    @SerializedName("uName") val uName: String?,
    @SerializedName("uImage") val uImage: String?,
    @SerializedName("uProvider") val uProvider: String?,
)