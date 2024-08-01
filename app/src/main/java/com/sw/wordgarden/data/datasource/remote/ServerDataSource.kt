package com.sw.wordgarden.data.datasource.remote

import com.sw.wordgarden.data.dto.QuizListDto
import com.sw.wordgarden.data.dto.SignUpDto
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
    suspend fun shareQuiz(quizTitle: String, friendUid: String)

    //words
    suspend fun insertLikedWord(word: WordDto)
    suspend fun deleteLikedWord(wordId: String)
    suspend fun getLikedWordList(): List<WordDto>?
    suspend fun getWeeklyWordList(): List<WordDto>?

    //quizzes
    suspend fun insertQuizList(quizList: QuizListDto)
    suspend fun deleteQuizList(quizListId: String)
    suspend fun getQuizListByType(type: Boolean): QuizListDto? //0: 4지선다, 1: OX 퀴즈 (default: 0)
    suspend fun getQuizListAllType(): QuizListDto? //각 타입별 5개씩
    suspend fun getQuizListMadeByUser(): List<QuizListDto>?
    suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<QuizListDto>?

    //garden
    suspend fun updateTree(treeId: String)
    suspend fun getTreeList(): List<TreeDto>?
}