package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class AddRequestFriendDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("friendUrl") val friendUrl: String?,
)