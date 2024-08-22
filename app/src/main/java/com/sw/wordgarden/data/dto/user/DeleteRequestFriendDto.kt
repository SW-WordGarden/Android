package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class DeleteRequestFriendDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("friendId") val friendUid: String?,
)