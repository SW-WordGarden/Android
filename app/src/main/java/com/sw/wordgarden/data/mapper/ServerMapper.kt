package com.sw.wordgarden.data.mapper

import com.sw.wordgarden.data.dto.AnswerDto
import com.sw.wordgarden.data.dto.QuestionDto
import com.sw.wordgarden.data.dto.SelfQuizDto
import com.sw.wordgarden.data.dto.QuestionResultDto
import com.sw.wordgarden.data.dto.QuizSummaryDto
import com.sw.wordgarden.data.dto.SelfQuizCreatorInfoDto
import com.sw.wordgarden.data.dto.SignUpDto
import com.sw.wordgarden.data.dto.SolveQuizDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.UserDto
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.domain.entity.AnswerEntity
import com.sw.wordgarden.domain.entity.QuestionEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.entity.QuestionResultEntity
import com.sw.wordgarden.domain.entity.QuizSummaryEntity
import com.sw.wordgarden.domain.entity.SelfQuizCreatorInfoEntity
import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.domain.entity.SolveQuizEntity
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

    fun AnswerEntity.toDto() = AnswerDto(
        questionId = questionId,
        userAnswer = userAnswer
    )

    fun AnswerDto.toEntity() = AnswerEntity(
        questionId = questionId,
        userAnswer = userAnswer
    )

    fun SolveQuizEntity.toDto() = SolveQuizDto(
        uid = uid,
        quizTitle = quizTitle,
        answers = answerEntityToDto(answers)
    )

    fun SolveQuizDto.toEntity() = SolveQuizEntity(
        uid = uid,
        quizTitle = quizTitle,
        answers = answerDtoToEntity(answers)
    )

    fun SelfQuizCreatorInfoEntity.toDto() = SelfQuizCreatorInfoDto(
        thumbnail = thumbnail,
        nickname = nickname,
        quizTitle = quizTitle
    )

    fun SelfQuizCreatorInfoDto.toEntity() = SelfQuizCreatorInfoEntity(
        thumbnail = thumbnail,
        nickname = nickname,
        quizTitle = quizTitle
    )

    private fun quizDtoToEntity(quiz: List<QuestionDto>?): List<QuestionEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizEntityToDto(quiz: List<QuestionEntity>?): List<QuestionDto> =
        quiz?.map { it.toDto() } ?: emptyList()

    private fun quizResultDtoToEntity(quiz: List<QuestionResultDto>?): List<QuestionResultEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizResultEntityToDto(quiz: List<QuestionResultEntity>?): List<QuestionResultDto> =
        quiz?.map { it.toDto() } ?: emptyList()

    private fun answerEntityToDto(answer: List<AnswerEntity>?): List<AnswerDto> =
        answer?.map { it.toDto() } ?: emptyList()

    private fun answerDtoToEntity(answer: List<AnswerDto>?): List<AnswerEntity> =
        answer?.map { it.toEntity() } ?: emptyList()

    //garden
    fun TreeDto.toEntity() = TreeEntity(
        id = id,
        name = name,
        image = image,
        growth = growth
    )
}