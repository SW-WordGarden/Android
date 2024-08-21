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
import com.sw.wordgarden.data.dto.quiz.WqResponseDto
import com.sw.wordgarden.data.dto.quiz.WqStateDto
import com.sw.wordgarden.data.dto.quiz.WqSubmissionDto
import com.sw.wordgarden.data.dto.quiz.WqWrongAnswerDto
import com.sw.wordgarden.data.dto.user.ReportInfoDto
import com.sw.wordgarden.data.dto.user.UserInfoDto

interface ServerDataSource {
    //user - login
    suspend fun insertUser(loginRequestDto: LoginRequestDto)
    suspend fun getUserInfoForLogin(uid: String): UserDto?
    suspend fun sendFirebaseToken(token: String)

    //user - mypage
    suspend fun getUserInfoForMypage(): UserInfoDto?
    suspend fun updateUserNickname(nickname: String)
    suspend fun getFriends(): List<UserDto>?
    suspend fun reportUser(reportInfo: ReportInfoDto)


    //words
    suspend fun insertLikedWord(word: WordDto)
    suspend fun deleteLikedWord(wordId: String)
    suspend fun getLikedWordList(): List<WordDto>?
    suspend fun getWeeklyWordList(): List<WordDto>?


    //quiz - wq
    suspend fun getGeneratedWq(): List<WqResponseDto>?
    suspend fun submitWq(wqSubmission: WqSubmissionDto)
    suspend fun getWqState(): WqStateDto?
    suspend fun getWrongWqs(): List<WqWrongAnswerDto>?
    suspend fun getSolvedWqTitles(): Set<String>?
    suspend fun getSolvedWq(title: String): List<WqResponseDto>?

    //quiz - sq
    suspend fun createNewSq(sqDto: SqDto)
    suspend fun getUserSqTitles(): List<QuizSummaryDto>?
    suspend fun getUserSq(creatorUid: String, quizId: String): SqDto?
    suspend fun getSq(quizId: String): List<SqQuestionAnswerDto>?
    suspend fun submitSq(solvedQuiz: SqSolveQuizDto)
    suspend fun getSolvedSqTitles(): List<QuizSummaryDto>?
    suspend fun getSolvedSq(title: String): SqDto?
    suspend fun getSqCreatorInfo(quizId: String): SqCreatorInfoDto?
    suspend fun shareQuiz(quizId: String, friendUid: String)


    //garden
    suspend fun updateTree(treeId: String)
    suspend fun getTreeList(): List<TreeDto>?
}