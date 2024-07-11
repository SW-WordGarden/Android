package com.sw.wordgarden.data.datasource.remote

import com.sw.wordgarden.data.dto.QuizListDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.UserDto
import com.sw.wordgarden.data.dto.WordDto
import java.util.Date

interface ServerDataSource {
    //user
    suspend fun insertUser(userDto: UserDto)
    suspend fun deleteUser()
    suspend fun updateUser(userDto: UserDto)
    suspend fun getUserInfo(): UserDto?

    //friends
    suspend fun insertFriend(friendId: String)
    suspend fun deleteFriend(friendId: String)
    suspend fun reportFriend(friendId: String, contents: String)
    suspend fun getFriendList(): List<UserDto>?

    //words
    suspend fun insertLikedWord(word: WordDto)
    suspend fun deleteLikedWord(wordId: String)
    suspend fun getLikedWordList(): List<WordDto>?
    suspend fun getWeeklyWordList(): List<WordDto>?

    //quizzes
    suspend fun insertQuizList(quizList: QuizListDto)
    suspend fun deleteQuizList(quizListId: String)
    suspend fun updateQuizList(quizList: QuizListDto)
    suspend fun getQuizListByType(type: Boolean): List<QuizListDto>?
    suspend fun getQuizListMadeByUser(): List<QuizListDto>?
    suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<QuizListDto>?

    //garden
    suspend fun updateTree(treeId: String)
    suspend fun getTreeList(): List<TreeDto>?
}