package com.sw.wordgarden.domain.entity.user

import com.google.gson.annotations.SerializedName

data class FriendListEntity(
    @SerializedName("userUUrl") val userUUrl: String?,
    @SerializedName("friends") val friends: List<FriendEntity>?,
)