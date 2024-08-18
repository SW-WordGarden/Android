package com.sw.wordgarden.data.datasource.remote

import com.sw.wordgarden.data.dto.QuizSummaryDto
import com.sw.wordgarden.data.dto.SelfQuizCreatorInfoDto
import com.sw.wordgarden.data.dto.SelfQuizDto
import com.sw.wordgarden.data.dto.SignUpDto
import com.sw.wordgarden.data.dto.SolveQuizDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.UserDto
import com.sw.wordgarden.data.dto.WordDto
import java.util.Date

interface ServerDataSource {
    //user
    suspend fun insertUser(signUpDto: SignUpDto)
    suspend fun deleteUser()
    suspend fun updateUser(userDto: UserDto)
    suspend fun getUserInfo(uid: String): UserDto?
    suspend fun sendFirebaseToken(token: String)

    //friends
    suspend fun insertFriend(friendId: String)
    suspend fun deleteFriend(friendId: String)
    suspend fun reportFriend(friendId: String, contents: String)
    suspend fun getFriendList(): List<UserDto>?
    suspend fun shareQuiz(quizId: String, friendUid: String)

    //words
    suspend fun insertLikedWord(word: WordDto)
    suspend fun deleteLikedWord(wordId: String)
    suspend fun getLikedWordList(): List<WordDto>?
    suspend fun getWeeklyWordList(): List<WordDto>?

    //quizzes
    suspend fun insertQuizList(selfQuiz: SelfQuizDto)
    suspend fun deleteQuizList(quizId: String)
    suspend fun getQuizListByType(type: Boolean): SelfQuizDto? //0: 4지선다, 1: OX 퀴즈 (default: 0)
    suspend fun getQuizListAllType(): SelfQuizDto? //각 타입별 5개씩
    suspend fun getQuizListMadeByUser(): List<QuizSummaryDto>?
    suspend fun getQuizListMadeByUserByQuizId(quizId: String): SelfQuizDto?
    suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<SelfQuizDto>?
    suspend fun getQuizBySelfQuizId(quizId: String): SelfQuizDto?
    suspend fun getSolvedSelfQuizTitleList(): List<String>?
    suspend fun getSolvedSelfQuizResult(title: String): SelfQuizDto?
    suspend fun getTodayQuiz(): SelfQuizDto?
    suspend fun getSelfQuizCreatorInfo(quizId: String): SelfQuizCreatorInfoDto?
    suspend fun sendQuizAnswer(quizResult: SelfQuizDto)
    suspend fun submitSelfQuiz(solvedQuiz: SolveQuizDto)

    //garden
    suspend fun updateTree(treeId: String)
    suspend fun getTreeList(): List<TreeDto>?
}