package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizModel(
    var qTitle: String?, //wq key
    val sqId: String?, //sq key
    val qaList: List<QAModel>?,
) : Parcelable
