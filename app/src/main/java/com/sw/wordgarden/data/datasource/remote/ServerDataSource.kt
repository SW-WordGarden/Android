package com.sw.wordgarden.data.datasource.remote

import com.sw.wordgarden.data.dto.quiz.SqQuestionAnswerDto
import com.sw.wordgarden.data.dto.quiz.QuizSummaryDto
import com.sw.wordgarden.data.dto.quiz.SqCreatorInfoDto
import com.sw.wordgarden.data.dto.quiz.SqDto
import com.sw.wordgarden.data.dto.user.LoginRequestDto
import com.sw.wordgarden.data.dto.quiz.SqSolveQuizDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.user.UserDto
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.data.dto.alarm.AlarmDetailDto
import com.sw.wordgarden.data.dto.alarm.AlarmDto
import com.sw.wordgarden.data.dto.quiz.SqCreatedInfoDto
import com.sw.wordgarden.data.dto.quiz.WqResponseDto
import com.sw.wordgarden.data.dto.quiz.WqStateDto
import com.sw.wordgarden.data.dto.quiz.WqSubmissionDto
import com.sw.wordgarden.data.dto.quiz.WqWrongAnswerDto
import com.sw.wordgarden.data.dto.user.FriendListDto
import com.sw.wordgarden.data.dto.user.UserInfoDto
import retrofit2.Response

interface ServerDataSource {
    //user - login
    suspend fun insertUser(loginRequestDto: LoginRequestDto)
    suspend fun getUserInfoForLogin(uid: String): UserDto?
    suspend fun updateFcmToken(token: String)

    //user - mypage
    suspend fun getUserInfoForMypage(): UserInfoDto?
    suspend fun updateUserNickname(nickname: String)
    suspend fun updateUserImage(image: String)
    suspend fun getFriends(): FriendListDto?

    //user - mypage - friend
    suspend fun addFriend(friendUrl: String)
    suspend fun deleteFriend(friendUid: String)
    suspend fun reportUser(friendUid: String, contents: String?)

    //alarm
    suspend fun makeSharingAlarm(toUserId: String, quizId: String)
    suspend fun getAlarms(): List<AlarmDto>?
    suspend fun getAlarmDetail(alarmId: String): AlarmDetailDto?
    suspend fun deleteAlarm(alarmId: String)

    //words
    suspend fun getWeeklyCategoryWordList(category:String) : List<WordDto>?
    suspend fun getDetailWord(wordId: String) : WordDto?
    suspend fun insertLikedWord( wordId: String, isLiked :Boolean)
    suspend fun getWordLikedStatus(wordId: String) : Boolean?
    suspend fun getWeeklyWordList() : List<WordDto>?
    suspend fun getRandomWord() : WordDto?

    //quiz - wq
    suspend fun getGeneratedWq(): List<WqResponseDto>?
    suspend fun submitWq(wqSubmission: WqSubmissionDto)
    suspend fun getWqState(): WqStateDto?
    suspend fun getWrongWqs(): List<WqWrongAnswerDto>?
    suspend fun getSolvedWqTitles(): Set<String>?
    suspend fun getWqOrSolvedWq(title: String, isSolved: Boolean): List<WqResponseDto>?

    //quiz - sq
    suspend fun createNewSq(sqDto: SqDto): SqCreatedInfoDto?
    suspend fun getUserSqTitles(): List<QuizSummaryDto>?
    suspend fun getUserSq(creatorUid: String?, quizId: String): SqDto?
    suspend fun getSq(quizId: String): List<SqQuestionAnswerDto>?
    suspend fun submitSq(solvedQuiz: SqSolveQuizDto)
    suspend fun getSolvedSqTitles(): List<QuizSummaryDto>?
    suspend fun getSolvedSq(title: String): SqDto?
    suspend fun getSqCreatorInfo(quizId: String): SqCreatorInfoDto?


    //garden
    suspend fun getGrowInfo(): TreeDto?
}