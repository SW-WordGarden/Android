package com.sw.wordgarden.presentation.mapper

import com.sw.wordgarden.domain.entity.QuizEntity
import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.entity.QuizResultEntity
import com.sw.wordgarden.presentation.model.QuizListModel
import com.sw.wordgarden.presentation.model.QuizModel
import com.sw.wordgarden.presentation.model.QuizResultModel

object ModelMapper {
    //quizzes
    fun QuizModel.toEntity() = QuizEntity(
        question = question,
        answer = answer,
        answerNumber = answerNumber,
    )

    fun QuizEntity.toModel() = QuizModel(
        question = question,
        answer = answer,
        answerNumber = answerNumber,
    )

    fun QuizListModel.toEntity() = QuizListEntity(
        title = title,
        quiz = quizModelToEntity(quiz),
        quizResult = quizResultModelToEntity(quizResult),
    )

    fun QuizListEntity.toModel() = QuizListModel(
        title = title,
        quiz = quizEntityToModel(quiz),
        quizResult = quizResultEntityToModel(quizResult)
    )

    fun QuizResultModel.toEntity() = QuizResultEntity(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        questionNumber = questionNumber,
    )

    fun QuizResultEntity.toModel() = QuizResultModel(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        questionNumber = questionNumber,
    )

    private fun quizModelToEntity(quiz: List<QuizModel>?): List<QuizEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizEntityToModel(quiz: List<QuizEntity>?): List<QuizModel> =
        quiz?.map { it.toModel() } ?: emptyList()

    private fun quizResultModelToEntity(quiz: List<QuizResultModel>?): List<QuizResultEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizResultEntityToModel(quiz: List<QuizResultEntity>?): List<QuizResultModel> =
        quiz?.map { it.toModel() } ?: emptyList()
}