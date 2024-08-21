package com.sw.wordgarden.domain.entity.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequestEntity (
    val uid: String,
    val nickname: String,
    val provider: String,
): Parcelable