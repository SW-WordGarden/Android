package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class RequestFriendDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("friendUrl") val friendUrl: String?,
)