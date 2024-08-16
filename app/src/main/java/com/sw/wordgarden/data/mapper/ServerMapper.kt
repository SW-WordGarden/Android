package com.sw.wordgarden.data.mapper

import com.sw.wordgarden.data.dto.QuestionDto
import com.sw.wordgarden.data.dto.SelfQuizDto
import com.sw.wordgarden.data.dto.QuestionResultDto
import com.sw.wordgarden.data.dto.QuizSummaryDto
import com.sw.wordgarden.data.dto.SignUpDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.UserDto
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.domain.entity.QuestionEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.entity.QuestionResultEntity
import com.sw.wordgarden.domain.entity.QuizSummaryEntity
import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.entity.UserEntity
import com.sw.wordgarden.domain.entity.WordEntity

object ServerMapper {
    //user
    fun UserDto.toEntity() = UserEntity(
        uid = uid,
        point = point,
        rank = rank,
        nickname = nickname,
        email = email,
        thumbnail = thumbnail,
        url = url
    )

    fun SignUpEntity.toDto() = SignUpDto(
        uid = uid,
        nickname = nickname,
        provider = provider,
    )

    //friends

    //words
    fun WordDto.toEntity() = WordEntity(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail,
        category = category
    )

    //quizzes
    fun QuestionDto.toEntity() = QuestionEntity(
        question = question,
        answer = answer,
        questionNumber = questionNumber,
    )

    fun QuestionEntity.toDto() = QuestionDto(
        question = question,
        answer = answer,
        questionNumber = questionNumber,
    )

    fun SelfQuizDto.toEntity() = SelfQuizEntity(
        quizId = quizId,
        title = title,
        quiz = quizDtoToEntity(quiz),
        quizResult = quizResultDtoToEntity(quizResult),
    )

    fun SelfQuizEntity.toDto() = SelfQuizDto(
        quizId = quizId,
        uid = "",
        title = title,
        quiz = quizEntityToDto(quiz),
        quizResult = quizResultEntityToDto(quizResult)
    )

    fun QuestionResultDto.toEntity() = QuestionResultEntity(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        questionNumber = answerNumber,
    )

    fun QuestionResultEntity.toDto() = QuestionResultDto(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        answerNumber = questionNumber,
    )

    fun QuizSummaryDto.toEntity() = QuizSummaryEntity(
        quizId = quizId,
        title = title
    )

    private fun quizDtoToEntity(quiz: List<QuestionDto>?): List<QuestionEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizEntityToDto(quiz: List<QuestionEntity>?): List<QuestionDto> =
        quiz?.map { it.toDto() } ?: emptyList()

    private fun quizResultDtoToEntity(quiz: List<QuestionResultDto>?): List<QuestionResultEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizResultEntityToDto(quiz: List<QuestionResultEntity>?): List<QuestionResultDto> =
        quiz?.map { it.toDto() } ?: emptyList()

    //garden
    fun TreeDto.toEntity() = TreeEntity(
        id = id,
        name = name,
        image = image,
        growth = growth
    )
}