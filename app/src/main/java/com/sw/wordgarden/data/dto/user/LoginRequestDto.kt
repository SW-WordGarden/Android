package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class LoginRequestDto (
    @SerializedName("uid") val uid: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("provider") val provider: String,
)