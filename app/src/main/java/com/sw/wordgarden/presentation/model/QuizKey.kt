package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizKey(
    //var creatorUid: String?,
    var qTitle: String?,
    var sqId: String?,
    var isWq: Boolean?
) : Parcelable
