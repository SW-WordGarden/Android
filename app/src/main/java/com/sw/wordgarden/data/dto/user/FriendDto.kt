package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class FriendDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("profileImg") val profileImg: String?,
)