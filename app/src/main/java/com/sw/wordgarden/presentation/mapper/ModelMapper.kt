package com.sw.wordgarden.presentation.mapper

import com.sw.wordgarden.domain.entity.QuestionEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.entity.QuestionResultEntity
import com.sw.wordgarden.domain.entity.QuizSummaryEntity
import com.sw.wordgarden.presentation.model.SelfQuizModel
import com.sw.wordgarden.presentation.model.QuestionModel
import com.sw.wordgarden.presentation.model.QuestionResultModel
import com.sw.wordgarden.presentation.model.QuizSummaryModel

object ModelMapper {
    //quizzes
    fun QuestionModel.toEntity() = QuestionEntity(
        question = question,
        answer = answer,
        questionNumber = questionNumber,
    )

    fun QuestionEntity.toModel() = QuestionModel(
        question = question,
        answer = answer,
        questionNumber = questionNumber,
    )

    fun SelfQuizModel.toEntity() = SelfQuizEntity(
        quizId = quizId,
        title = title,
        quiz = quizModelToEntity(quiz),
        quizResult = quizResultModelToEntity(quizResult),
    )

    fun SelfQuizEntity.toModel() = SelfQuizModel(
        quizId = quizId,
        title = title,
        quiz = quizEntityToModel(quiz),
        quizResult = quizResultEntityToModel(quizResult)
    )

    fun QuestionResultModel.toEntity() = QuestionResultEntity(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        questionNumber = questionNumber,
    )

    fun QuestionResultEntity.toModel() = QuestionResultModel(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        questionNumber = questionNumber,
    )

    fun QuizSummaryEntity.toModel() = QuizSummaryModel(
        quizId = quizId,
        title = title
    )

    private fun quizModelToEntity(quiz: List<QuestionModel>?): List<QuestionEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizEntityToModel(quiz: List<QuestionEntity>?): List<QuestionModel> =
        quiz?.map { it.toModel() } ?: emptyList()

    private fun quizResultModelToEntity(quiz: List<QuestionResultModel>?): List<QuestionResultEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizResultEntityToModel(quiz: List<QuestionResultEntity>?): List<QuestionResultModel> =
        quiz?.map { it.toModel() } ?: emptyList()
}