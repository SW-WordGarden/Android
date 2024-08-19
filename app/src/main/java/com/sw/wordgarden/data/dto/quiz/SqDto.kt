package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class SqDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("quizTitle") val quizTitle: String?,
    @SerializedName("sqId") val sqId: String?,
    @SerializedName("questionsAndAnswers") val questionsAndAnswers: List<SqQuestionAnswerDto>?,
    @SerializedName("sqresults") val sqresults : List<SqresultDto>?,
)