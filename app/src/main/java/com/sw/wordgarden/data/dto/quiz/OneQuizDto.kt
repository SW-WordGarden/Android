package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class OneQuizDto(
    @SerializedName("wqId") val id: String,
    @SerializedName("wqQuestion") val question : String,
    @SerializedName("word") val title:String,
    @SerializedName("correctAnswer") val correctAnswer:String,
)
