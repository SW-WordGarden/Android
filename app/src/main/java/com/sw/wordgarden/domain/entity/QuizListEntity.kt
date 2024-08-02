package com.sw.wordgarden.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizListEntity(
    @SerializedName("sqQnum") val title: String?,
    @SerializedName("sqQnum") val quiz: List<QuizEntity>?,
    @SerializedName("sqQnum") val quizResult: List<QuizResultEntity>?,
) : Parcelable