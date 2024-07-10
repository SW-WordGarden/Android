package com.sw.wordgarden.domain.entity

data class QuizListEntity(
    val id: String?,
    val title: String?,
    val quiz: List<QuizEntity>?,
    val creatorId: String?,
    val type: Boolean?,
    val url: String,
)