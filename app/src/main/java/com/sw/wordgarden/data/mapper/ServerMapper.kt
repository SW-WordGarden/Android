package com.sw.wordgarden.data.mapper

import com.sw.wordgarden.data.dto.quiz.SqAnswerDto
import com.sw.wordgarden.data.dto.quiz.GroupedSqDto
import com.sw.wordgarden.data.dto.quiz.SqQuestionAnswerDto
import com.sw.wordgarden.data.dto.quiz.SqDto
import com.sw.wordgarden.data.dto.quiz.SqresultDto
import com.sw.wordgarden.data.dto.quiz.SqQuizSummaryDto
import com.sw.wordgarden.data.dto.quiz.SqCreatorInfoDto
import com.sw.wordgarden.data.dto.user.LoginRequestDto
import com.sw.wordgarden.data.dto.quiz.SqSolveQuizDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.user.UserDto
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.data.dto.quiz.SqQuestionDto
import com.sw.wordgarden.data.dto.quiz.ShareRequestDto
import com.sw.wordgarden.data.dto.quiz.WqAnswerDto
import com.sw.wordgarden.data.dto.quiz.WqResponseDto
import com.sw.wordgarden.data.dto.quiz.WqStateDto
import com.sw.wordgarden.data.dto.quiz.WqSubmissionDto
import com.sw.wordgarden.data.dto.quiz.WqWrongAnswerDto
import com.sw.wordgarden.data.dto.user.CustomQuizDto
import com.sw.wordgarden.data.dto.user.ReportInfoDto
import com.sw.wordgarden.data.dto.user.SolvedQuizDto
import com.sw.wordgarden.data.dto.user.UserInfoDto
import com.sw.wordgarden.domain.entity.quiz.SqAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.GroupedSqEntity
import com.sw.wordgarden.domain.entity.quiz.SqQuestionAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.entity.quiz.SqresultEntity
import com.sw.wordgarden.domain.entity.quiz.SqQuizSummaryEntity
import com.sw.wordgarden.domain.entity.quiz.SqCreatorInfoEntity
import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.domain.entity.quiz.SqQuestionEntity
import com.sw.wordgarden.domain.entity.quiz.ShareRequestEntity
import com.sw.wordgarden.domain.entity.quiz.SqSolveQuizEntity
import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.entity.user.UserEntity
import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.entity.quiz.WqAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.entity.quiz.WqStateEntity
import com.sw.wordgarden.domain.entity.quiz.WqSubmissionEntity
import com.sw.wordgarden.domain.entity.quiz.WqWrongAnswerEntity
import com.sw.wordgarden.domain.entity.user.CustomQuizEntity
import com.sw.wordgarden.domain.entity.user.ReportInfoEntity
import com.sw.wordgarden.domain.entity.user.SolvedQuizEntity
import com.sw.wordgarden.domain.entity.user.UserInfoEntity

object ServerMapper {
    //user
    fun UserDto.toEntity() = UserEntity(
        uid = uid,
        uRank = uRank,
        uPoint = uPoint,
        uName = uName,
        uImage = uImage,
        uProvider = uProvider,
    )

    fun LoginRequestEntity.toDto() = LoginRequestDto(
        uid = uid,
        nickname = nickname,
        provider = provider,
    )

    fun UserInfoDto.toEntity() = UserInfoEntity(
        profileImage = profileImage,
        point = point,
        rank = rank,
        randomFriends = randomFriends,
        name = name,
        all = all,
        right = right,
        latestCustomQuiz = latestCustomQuiz?.toEntity(),
        latestSolvedQuiz = latestSolvedQuiz?.toEntity(),
    )

    fun CustomQuizDto.toEntity() = CustomQuizEntity(
        sqTitle = sqTitle,
        sqId = sqId,
    )

    fun SolvedQuizDto.toEntity() = SolvedQuizEntity(
        quizId = quizId,
        quizTitle = quizTitle,
    )

    fun ReportInfoEntity.toDto() = ReportInfoDto(
        reporterId = reporterId,
        reportedId = reportedId,
    )

    //words
    fun WordDto.toEntity() = WordEntity(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail,
        category = category
    )

    //quiz - wq
    fun WqAnswerDto.toEntity() = WqAnswerEntity(
        wqId = wqId,
        uWqA = uWqA,
    )

    fun WqAnswerEntity.toDto() = WqAnswerDto(
        wqId = wqId,
        uWqA = uWqA,
    )

    fun WqResponseDto.toEntity() = WqResponseEntity(
        wqId = wqId,
        wqQuestion = wqQuestion,
        wqTitle = wqTitle,
        wordId = wordId,
        word = word,
        questionType = questionType,
        options = options,
        userAnswer = userAnswer,
        correctAnswer = correctAnswer,
    )

    fun WqResponseEntity.toDto() = WqResponseDto(
        wqId = wqId,
        wqQuestion = wqQuestion,
        wqTitle = wqTitle,
        wordId = wordId,
        word = word,
        questionType = questionType,
        options = options,
        userAnswer = userAnswer,
        correctAnswer = correctAnswer,
    )

    fun WqSubmissionDto.toEntity() = WqSubmissionEntity(
        uid = uid,
        answers = answersDtoToEntity(answers)
    )

    fun WqWrongAnswerDto.toEntity() = WqWrongAnswerEntity(
        wqId = wqId,
        word = word,
        wordInfo = wordInfo,
    )

    private fun answersDtoToEntity(answers: List<WqAnswerDto>?): List<WqAnswerEntity>? {
        return answers?.map { it.toEntity() }
    }

    fun WqSubmissionEntity.toDto() = WqSubmissionDto(
        uid = uid,
        answers = answersEntityToDto(answers)
    )

    private fun answersEntityToDto(answers: List<WqAnswerEntity>?): List<WqAnswerDto>? {
        return answers?.map { it.toDto() }
    }

    fun WqStateDto.toEntity() = WqStateEntity(
        totalQuestions = totalQuestions,
        correctAnswers = correctAnswers
    )

    //quiz - sq
    fun SqAnswerDto.toEntity() = SqAnswerEntity(
        questionId = questionId,
        userAnswer = userAnswer
    )

    fun SqAnswerEntity.toDto() = SqAnswerDto(
        questionId = questionId,
        userAnswer = userAnswer
    )

    fun GroupedSqDto.toEntity() = GroupedSqEntity(
        uid = uid,
        quizTitle = quizTitle,
        questionsAndAnswers = questionsAndAnswersDtoToEntity(questionsAndAnswers)
    )

    private fun questionsAndAnswersDtoToEntity(questionsAndAnswers: List<SqQuestionAnswerDto>?): List<SqQuestionAnswerEntity>? {
        return questionsAndAnswers?.map { it.toEntity() }
    }

    fun GroupedSqEntity.toDto() = GroupedSqDto(
        uid = uid,
        quizTitle = quizTitle,
        questionsAndAnswers = questionsAndAnswersEntityToDto(questionsAndAnswers)
    )

    private fun questionsAndAnswersEntityToDto(questionsAndAnswers: List<SqQuestionAnswerEntity>?): List<SqQuestionAnswerDto>? {
        return questionsAndAnswers?.map { it.toDto() }
    }

    fun SqQuestionAnswerDto.toEntity() = SqQuestionAnswerEntity(
        question = question,
        answer = answer,
        sqQnum = sqQnum,
    )

    fun SqQuestionAnswerEntity.toDto() = SqQuestionAnswerDto(
        question = question,
        answer = answer,
        sqQnum = sqQnum,
    )

    fun SqQuestionDto.toEntity() = SqQuestionEntity(
        id = id,
        question = question,
        questionNumber = questionNumber,
    )

    fun SqQuestionEntity.toDto() = SqQuestionDto(
        id = id,
        question = question,
        questionNumber = questionNumber,
    )

    fun SqQuizSummaryDto.toEntity() = SqQuizSummaryEntity(
        quizId = quizId,
        title = title
    )

    fun SqQuizSummaryEntity.toDto() = SqQuizSummaryDto(
        quizId = quizId,
        title = title
    )

    fun ShareRequestDto.toEntity() = ShareRequestEntity(
        fromUserId = fromUserId,
        toUserId = toUserId,
        quizId = quizId,
    )

    fun ShareRequestEntity.toDto() = ShareRequestDto(
        fromUserId = fromUserId,
        toUserId = toUserId,
        quizId = quizId,
    )

    fun SqSolveQuizDto.toEntity() = SqSolveQuizEntity(
        uid = uid,
        quizTitle = quizTitle,
        answers = answerDtoToEntity(answers)
    )

    private fun answerDtoToEntity(answer: List<SqAnswerDto>?): List<SqAnswerEntity> =
        answer?.map { it.toEntity() } ?: emptyList()

    fun SqSolveQuizEntity.toDto() = SqSolveQuizDto(
        uid = uid,
        quizTitle = quizTitle,
        answers = answerEntityToDto(answers)
    )

    private fun answerEntityToDto(answer: List<SqAnswerEntity>?): List<SqAnswerDto> =
        answer?.map { it.toDto() } ?: emptyList()

    fun SqCreatorInfoDto.toEntity() = SqCreatorInfoEntity(
        thumbnail = thumbnail,
        nickname = nickname,
        quizTitle = quizTitle
    )

    fun SqCreatorInfoEntity.toDto() = SqCreatorInfoDto(
        thumbnail = thumbnail,
        nickname = nickname,
        quizTitle = quizTitle
    )

    fun SqDto.toEntity() = SqEntity(
        uid = uid,
        sqId = sqId,
        quizTitle = quizTitle,
        questionsAndAnswers = quizDtoToEntity(questionsAndAnswers),
        sqresults = quizResultDtoToEntity(sqresults),
    )

    private fun quizDtoToEntity(quiz: List<SqQuestionAnswerDto>?): List<SqQuestionAnswerEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    private fun quizResultDtoToEntity(quiz: List<SqresultDto>?): List<SqresultEntity> =
        quiz?.map { it.toEntity() } ?: emptyList()

    fun SqEntity.toDto() = SqDto(
        sqId = sqId,
        uid = "",
        quizTitle = quizTitle,
        questionsAndAnswers = quizEntityToDto(questionsAndAnswers),
        sqresults = quizResultEntityToDto(sqresults)
    )

    private fun quizEntityToDto(quiz: List<SqQuestionAnswerEntity>?): List<SqQuestionAnswerDto> =
        quiz?.map { it.toDto() } ?: emptyList()

    private fun quizResultEntityToDto(quiz: List<SqresultEntity>?): List<SqresultDto> =
        quiz?.map { it.toDto() } ?: emptyList()

    fun SqresultDto.toEntity() = SqresultEntity(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        sqQnum = sqQnum,
    )

    fun SqresultEntity.toDto() = SqresultDto(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        sqQnum = sqQnum,
    )

    //garden
    fun TreeDto.toEntity() = TreeEntity(
        id = id,
        name = name,
        image = image,
        growth = growth
    )
}