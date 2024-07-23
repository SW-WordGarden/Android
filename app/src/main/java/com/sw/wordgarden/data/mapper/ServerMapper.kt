package com.sw.wordgarden.data.mapper

import com.sw.wordgarden.data.dto.QuizDto
import com.sw.wordgarden.data.dto.QuizListDto
import com.sw.wordgarden.data.dto.QuizResultDto
import com.sw.wordgarden.data.dto.SignUpDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.UserDto
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.domain.entity.QuizEntity
import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.entity.QuizResultEntity
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
    fun QuizDto.toEntity() = QuizEntity(
        question = question,
        answer = answer,
        answerNumber = answerNumber,
    )

    fun QuizEntity.toDto() = QuizDto(
        question = question,
        answer = answer,
        answerNumber = answerNumber,
    )

    fun QuizListDto.toEntity() = QuizListEntity(
        title = title,
        quiz = quizDtoToEntity(quiz),
        quizResult = quizResultDtoToEntity(quizResult),
    )

    fun QuizListEntity.toDto() = QuizListDto(
        uid = "",
        title = title,
        quiz = quizEntityToDto(quiz),
        quizResult = quizResultEntityToDto(quizResult)
    )

    fun QuizResultDto.toEntity() = QuizResultEntity(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        answerNumber = answerNumber,
    )

    fun QuizResultEntity.toDto() = QuizResultDto(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        answerNumber = answerNumber,
    )

    private fun quizDtoToEntity(quiz: List<QuizDto>?): List<QuizEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizEntityToDto(quiz: List<QuizEntity>?): List<QuizDto> =
        quiz?.map { it.toDto() } ?: emptyList()

    private fun quizResultDtoToEntity(quiz: List<QuizResultDto>?): List<QuizResultEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizResultEntityToDto(quiz: List<QuizResultEntity>?): List<QuizResultDto> =
        quiz?.map { it.toDto() } ?: emptyList()

    //garden
    fun TreeDto.toEntity() = TreeEntity(
        id = id,
        name = name,
        image = image,
        growth = growth
    )
}