package com.sw.wordgarden.data.mapper

import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.data.dto.alarm.AlarmDetailDto
import com.sw.wordgarden.data.dto.alarm.AlarmDto
import com.sw.wordgarden.data.dto.alarm.ShareRequestDto
import com.sw.wordgarden.data.dto.quiz.QuizSummaryDto
import com.sw.wordgarden.data.dto.quiz.SqAnswerDto
import com.sw.wordgarden.data.dto.quiz.SqCreatedInfoDto
import com.sw.wordgarden.data.dto.quiz.SqCreatorInfoDto
import com.sw.wordgarden.data.dto.quiz.SqDto
import com.sw.wordgarden.data.dto.quiz.SqQuestionAnswerDto
import com.sw.wordgarden.data.dto.quiz.SqSolveQuizDto
import com.sw.wordgarden.data.dto.quiz.SqresultDto
import com.sw.wordgarden.data.dto.quiz.WqAnswerDto
import com.sw.wordgarden.data.dto.quiz.WqResponseDto
import com.sw.wordgarden.data.dto.quiz.WqStateDto
import com.sw.wordgarden.data.dto.quiz.WqSubmissionDto
import com.sw.wordgarden.data.dto.quiz.WqWrongAnswerDto
import com.sw.wordgarden.data.dto.user.CustomQuizDto
import com.sw.wordgarden.data.dto.user.FriendDto
import com.sw.wordgarden.data.dto.user.FriendListDto
import com.sw.wordgarden.data.dto.user.LoginRequestDto
import com.sw.wordgarden.data.dto.user.SolvedQuizDto
import com.sw.wordgarden.data.dto.user.UserDto
import com.sw.wordgarden.data.dto.user.UserInfoDto
import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.entity.alarm.AlarmDetailEntity
import com.sw.wordgarden.domain.entity.alarm.AlarmEntity
import com.sw.wordgarden.domain.entity.alarm.ShareRequestEntity
import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity
import com.sw.wordgarden.domain.entity.quiz.SqAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.SqCreatedInfoEntity
import com.sw.wordgarden.domain.entity.quiz.SqCreatorInfoEntity
import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.entity.quiz.SqQuestionAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.SqSolveQuizEntity
import com.sw.wordgarden.domain.entity.quiz.SqresultEntity
import com.sw.wordgarden.domain.entity.quiz.WqAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.entity.quiz.WqStateEntity
import com.sw.wordgarden.domain.entity.quiz.WqSubmissionEntity
import com.sw.wordgarden.domain.entity.quiz.WqWrongAnswerEntity
import com.sw.wordgarden.domain.entity.user.CustomQuizEntity
import com.sw.wordgarden.domain.entity.user.FriendEntity
import com.sw.wordgarden.domain.entity.user.FriendListEntity
import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.domain.entity.user.SolvedQuizEntity
import com.sw.wordgarden.domain.entity.user.UserEntity
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
        fcmToken = fcmToken,
    )

    fun UserInfoDto.toEntity() = UserInfoEntity(
        profileImage = profileImage,
        point = point,
        rank = rank,
        randomFriends = randomFriends?.map { it.toEntity() },
        name = name,
        uUrl = uUrl,
        all = all,
        right = right,
        latestCustomQuiz = latestCustomQuiz?.toEntity(),
        latestSolvedQuiz = latestSolvedQuiz?.toEntity(),
    )

    fun FriendDto.toEntity() = FriendEntity(
        uid = uid,
        nickname = nickname,
        profileImg = profileImg
    )

    fun CustomQuizDto.toEntity() = CustomQuizEntity(
        sqTitle = sqTitle,
        sqId = sqId,
    )

    fun SolvedQuizDto.toEntity() = SolvedQuizEntity(
        type = type,
        title = title,
    )

    fun FriendListDto.toEntity() = FriendListEntity(
        userUUrl = userUUrl,
        friends = friends?.map { it.toEntity() }
    )

    //share
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

    fun AlarmDto.toEntity() = AlarmEntity(
        alarmId = alarmId,
        content = content,
        isRead = isRead,
        createTime = createTime,
        fromUserUid = fromUserUid,
        fromUserName = fromUserName,
        toUserName = toUserName,
        quizType = quizType
    )

    fun AlarmDetailDto.toEntity() = AlarmDetailEntity(
        alarmId = alarmId,
        content = content,
        fromUserName = fromUserName,
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

    fun WqWrongAnswerDto.toEntity() = WqWrongAnswerEntity(
        wqId = wqId,
        word = word,
        wordInfo = wordInfo,
    )

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
    fun SqAnswerEntity.toDto() = SqAnswerDto(
        questionId = questionId,
        userAnswer = userAnswer
    )

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

    fun QuizSummaryDto.toEntity() = QuizSummaryEntity(
        quizId = quizId,
        title = title
    )

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

    fun SqCreatedInfoDto.toEntity() = SqCreatedInfoEntity(
        sqId = sqId,
        quizTitle = quizTitle,
    )

    //garden
    fun TreeDto.toEntity() = TreeEntity(
        id = id,
        name = name,
        image = image,
        growth = growth
    )
}