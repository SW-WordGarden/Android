package com.sw.wordgarden.domain.entity.user

import com.google.gson.annotations.SerializedName

data class FriendEntity(
    @SerializedName("uid") val uid: String?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("profileImg") val profileImg: String?,
)