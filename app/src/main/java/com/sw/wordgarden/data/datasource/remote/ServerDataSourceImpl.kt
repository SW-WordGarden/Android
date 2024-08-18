package com.sw.wordgarden.data.datasource.remote

import android.util.Log
import com.sw.wordgarden.data.datasource.local.LocalDataSource
import com.sw.wordgarden.data.datasource.remote.retrofit.Service
import com.sw.wordgarden.data.dto.QuizSummaryDto
import com.sw.wordgarden.data.dto.SelfQuizCreatorInfoDto
import com.sw.wordgarden.data.dto.SelfQuizDto
import com.sw.wordgarden.data.dto.SignUpDto
import com.sw.wordgarden.data.dto.SolveQuizDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.UserDto
import com.sw.wordgarden.data.dto.WordDto
import retrofit2.HttpException
import java.util.Date
import javax.inject.Inject

class ServerDataSourceImpl @Inject constructor(
    private val service: Service,
    private val localDataSource: LocalDataSource
) : ServerDataSource {

    private val TAG = "ServerDataSourceImpl"

    //user
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

    override suspend fun sendFirebaseToken(token: String) {
        try {
            val uid = getUid()

            val response = service.sendFirebaseToken(uid!!, token)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //friends
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
        return try {
            val uid = getUid()

            val response = service.getFriendList(uid!!)
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

    override suspend fun shareQuiz(quizId: String, friendUid: String) {
        try {
            val uid = getUid()

            val response = service.shareQuiz(uid!!, quizId, friendUid)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    //words
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

    //quizzes
    override suspend fun insertQuizList(selfQuiz: SelfQuizDto) {
        try {
            val uid = getUid()
            val quizListData = selfQuiz.copy(
                uid = uid
            )

            Log.i(TAG, "서버에 전달한 퀴즈 데이터 : $quizListData")

            val response = service.insertQuizList(quizListData)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun deleteQuizList(quizId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListByType(type: Boolean): SelfQuizDto? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListAllType(): SelfQuizDto? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListMadeByUser(): List<QuizSummaryDto>? {
        return try {
            val uid = getUid()

            val response = service.getQuizListMadeByUser(uid!!)
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

    override suspend fun getQuizListMadeByUserByQuizId(quizId: String): SelfQuizDto? {
        return try {
            val uid = getUid()

            val response = service.getQuizListMadeByUserBySelfQuizId(uid!!, quizId)
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

    override suspend fun getQuizListDoneByUserAndPeriod(
        startDate: Date,
        endDate: Date
    ): List<SelfQuizDto>? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizBySelfQuizId(quizId: String): SelfQuizDto? {
        return try {
            val response = service.getQuizBySelfQuizId(quizId)
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

    override suspend fun getSolvedSelfQuizTitleList(): List<String>? {
        return try {
            val uid = getUid()

            val response = service.getSolvedSelfQuizTitleList(uid!!)
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

    override suspend fun getSolvedSelfQuizResult(title: String): SelfQuizDto? {
        return try {
            val uid = getUid()

            val response = service.getSolvedSelfQuizResult(uid!!, title)
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

    override suspend fun getTodayQuiz(): SelfQuizDto? {
        return try {
            val uid = getUid()

            val response = service.getTodayQuiz(uid!!)
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

    override suspend fun getSelfQuizCreatorInfo(quizId: String): SelfQuizCreatorInfoDto? {
        return try {
            val response = service.getSelfQuizCreatorInfo(quizId)
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

    override suspend fun sendQuizAnswer(quizResult: SelfQuizDto) {
        try {
            val uid = getUid()

            val response = service.sendQuizAnswer(uid!!, quizResult)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun submitSelfQuiz(solvedQuiz: SolveQuizDto) {
        try {
            val response = service.submitSelfQuiz(solvedQuiz)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //garden
    override suspend fun updateTree(treeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getTreeList(): List<TreeDto>? {
        TODO("Not yet implemented")
    }

    private suspend fun getUid(): String? {
        return localDataSource.getUid()
    }
}