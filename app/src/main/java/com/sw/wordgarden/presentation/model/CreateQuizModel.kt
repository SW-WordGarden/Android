package com.sw.wordgarden.presentation.model

data class CreateQuizModel(
    var quizTitle: String,
    val quiz: List<CreateQAModel>
)
