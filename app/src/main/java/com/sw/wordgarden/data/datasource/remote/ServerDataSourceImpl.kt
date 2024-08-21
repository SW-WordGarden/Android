package com.sw.wordgarden.data.datasource.remote

import android.util.Log
import com.sw.wordgarden.data.datasource.local.LocalDataSource
import com.sw.wordgarden.data.datasource.remote.retrofit.Service
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
import retrofit2.HttpException
import javax.inject.Inject

class ServerDataSourceImpl @Inject constructor(
    private val service: Service,
    private val localDataSource: LocalDataSource
) : ServerDataSource {

    private val TAG = "ServerDataSourceImpl"

    //user - login
    override suspend fun insertUser(loginRequestDto: LoginRequestDto) {
        try {
            val response = service.insertUser(loginRequestDto)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getUserInfoForLogin(uid: String): UserDto? {
        return try {
            val response = service.getUserInfoForLogin(uid)
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

    //user - mypage
    override suspend fun getUserInfoForMypage(): UserInfoDto? {
        return try {
            val uid = getUid()

            val response = service.getUserInfoForMypage(uid!!)
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

    override suspend fun updateUserNickname(nickname: String) {
        try {
            val uid = getUid()

            val response = service.updateUserNickname(uid!!, nickname)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getFriends(): List<UserDto>? {
        return try {
            val uid = getUid()

            val response = service.getFriends(uid!!)
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

    override suspend fun reportUser(reportInfo: ReportInfoDto) {
        try {
            val uid = getUid()
            val contents = reportInfo.copy(
                reporterId = uid
            )

            val response = service.reportUser(contents)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
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


    //quiz - wq
    override suspend fun getGeneratedWq(): List<WqResponseDto>? {
        return try {
            val response = service.getGeneratedWq()
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

    override suspend fun submitWq(wqSubmission: WqSubmissionDto) {
        try {
            val uid = getUid()
            val request = WqSubmissionDto(
                uid = uid,
                answers = wqSubmission.answers,
            )
            val response = service.submitWq(request)

            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getWqState(): WqStateDto? {
        return try {
            val uid = getUid()

            val response = service.getWqState(uid!!)
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

    override suspend fun getWrongWqs(): List<WqWrongAnswerDto>? {
        return try {
            val uid = getUid()

            val response = service.getWrongWqs(uid!!)
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

    override suspend fun getSolvedWqTitles(): Set<String>? {
        return try {
            val uid = getUid()

            val response = service.getSolvedWqTitles(uid!!)
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

    override suspend fun getSolvedWq(title: String): List<WqResponseDto>? {
        return try {
            val uid = getUid()
            val response = service.getSolvedWq(title, uid!!)

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

    //quiz - sq
    override suspend fun createNewSq(sqDto: SqDto) {
        try {
            val uid = getUid()
            val quizListData = sqDto.copy(
                uid = uid
            )
            val response = service.createNewSq(quizListData)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getUserSqTitles(): List<QuizSummaryDto>? {
        return try {
            val uid = getUid()

            val response = service.getUserSqTitles(uid!!)
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

    override suspend fun getUserSq(creatorUid: String, quizId: String): SqDto? {
        return try {
            val uid = if (creatorUid == "") {
                getUid()
            } else {
                creatorUid
            }

            val response = service.getUserSq(uid!!, quizId)
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

    override suspend fun getSq(quizId: String): List<SqQuestionAnswerDto>? {
        return try {
            val response = service.getSq(quizId)
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

    override suspend fun submitSq(solvedQuiz: SqSolveQuizDto) {
        try {
            val response = service.submitSq(solvedQuiz)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getSolvedSqTitles(): List<QuizSummaryDto>? {
        return try {
            val uid = getUid()

            val response = service.getSolvedSqTitles(uid!!)
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

    override suspend fun getSolvedSq(title: String): SqDto? {
        return try {
            val uid = getUid()

            val response = service.getSolvedSq(uid!!, title)
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

    override suspend fun getSqCreatorInfo(quizId: String): SqCreatorInfoDto? {
        return try {
            val response = service.getSqCreatorInfo(quizId)
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


    //garden
    override suspend fun updateTree(treeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getTreeList(): List<TreeDto>? {
        TODO("Not yet implemented")
    }


    //not require server connection
    private suspend fun getUid(): String? {
        return localDataSource.getUid()
    }
}