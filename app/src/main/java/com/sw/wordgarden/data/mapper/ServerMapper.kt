package com.sw.wordgarden.data.mapper

import com.sw.wordgarden.data.dto.QuizDto
import com.sw.wordgarden.data.dto.QuizListDto
import com.sw.wordgarden.data.dto.SignUpDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.UserDto
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.domain.entity.QuizEntity
import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.entity.UserEntity
import com.sw.wordgarden.domain.entity.WordEntity

object ServerMapper {
    fun QuizDto.toEntity() = QuizEntity(
        id = id,
        question = question,
        answer = answer,
        answerNumber = answerNumber,
        check = check,
        checkTime = checkTime
    )

    fun QuizListDto.toEntity() = QuizListEntity(
        id = id,
        title = title,
        creatorId = creatorId,
        type = type,
        url = url,
        quiz = quizDtoToEntity(quiz)
    )

    private fun quizDtoToEntity(quiz: List<QuizDto>?): List<QuizEntity>? {
        val list: List<QuizEntity>? = null

        quiz?.forEach {
            list?.plus(it.toEntity())
        }

        return list
    }

    fun TreeDto.toEntity() = TreeEntity(
        id = id,
        name = name,
        image = image,
        growth = growth
    )

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

    fun WordDto.toEntity() = WordEntity(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail,
        category = category
    )
}