package com.sw.wordgarden.data.dto

data class QuizListDto(
    val id: String?,
    val title: String?,
    val quiz: List<QuizDto>?,
    val creatorId: String?,
    val type: Boolean?,
    val url: String,
)