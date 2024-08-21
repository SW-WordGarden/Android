package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class GroupedSqDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("quizTitle") val quizTitle: String?,
    @SerializedName("questionsAndAnswers") val questionsAndAnswers: List<SqQuestionAnswerDto>?,
)