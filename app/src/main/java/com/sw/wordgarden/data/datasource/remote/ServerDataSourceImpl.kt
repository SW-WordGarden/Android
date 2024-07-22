package com.sw.wordgarden.data.datasource.remote

import com.sw.wordgarden.data.dto.QuizListDto
import com.sw.wordgarden.data.dto.SignUpDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.UserDto
import com.sw.wordgarden.data.dto.WordDto
import retrofit2.HttpException
import java.util.Date
import javax.inject.Inject

class ServerDataSourceImpl @Inject constructor(
    private val service: Service
) : ServerDataSource {
    override suspend fun insertUser(signUpDto: SignUpDto) {
        try {
            val response = service.insertUser(signUpDto)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userDto: UserDto) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(uid: String): UserDto? {
        return try {
            val response = service.getUserInfo(uid)
            if (!response.isSuccessful) {
                throw HttpException(response)
            } else {
                response.body()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun insertFriend(friendId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFriend(friendId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun reportFriend(friendId: String, contents: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getFriendList(): List<UserDto>? {
        TODO("Not yet implemented")
    }

    override suspend fun insertLikedWord(word: WordDto) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLikedWord(wordId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getLikedWordList(): List<WordDto>? {
        TODO("Not yet implemented")
    }

    override suspend fun getWeeklyWordList(): List<WordDto>? {
        TODO("Not yet implemented")
    }

    override suspend fun insertQuizList(quizList: QuizListDto) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteQuizList(quizListId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListByType(type: Boolean): QuizListDto? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListAllType(): QuizListDto? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListMadeByUser(): List<QuizListDto>? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListDoneByUserAndPeriod(
        startDate: Date,
        endDate: Date
    ): List<QuizListDto>? {
        TODO("Not yet implemented")
    }

    override suspend fun updateTree(treeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getTreeList(): List<TreeDto>? {
        TODO("Not yet implemented")
    }

}