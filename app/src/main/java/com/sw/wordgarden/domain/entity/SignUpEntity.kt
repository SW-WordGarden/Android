package com.sw.wordgarden.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpEntity (
    val uid: String,
    val nickname: String,
    val provider: String,
): Parcelable