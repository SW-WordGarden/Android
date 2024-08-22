package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class FriendListDto(
    @SerializedName("userUUrl") val userUUrl: String?,
    @SerializedName("friends") val friends: List<FriendDto>?,
)