package com.sw.wordgarden.domain.entity

data class QuizListEntity(
    val id: String?,
    val title: String?,
    val quiz: List<QuizEntity>?,
    val creatorId: String?,
    val type: Boolean?, //0: 자체, 1: 커스텀
    val url: String,
)