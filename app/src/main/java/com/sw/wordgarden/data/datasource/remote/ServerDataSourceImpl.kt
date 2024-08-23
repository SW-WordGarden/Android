package com.sw.wordgarden.data.datasource.remote

import com.sw.wordgarden.data.datasource.local.LocalDataSource
import com.sw.wordgarden.data.datasource.remote.retrofit.Service
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.data.dto.alarm.AlarmDetailDto
import com.sw.wordgarden.data.dto.alarm.AlarmDto
import com.sw.wordgarden.data.dto.alarm.ShareRequestDto
import com.sw.wordgarden.data.dto.quiz.QuizSummaryDto
import com.sw.wordgarden.data.dto.quiz.SqCreatedInfoDto
import com.sw.wordgarden.data.dto.quiz.SqCreatorInfoDto
import com.sw.wordgarden.data.dto.quiz.SqDto
import com.sw.wordgarden.data.dto.quiz.SqQuestionAnswerDto
import com.sw.wordgarden.data.dto.quiz.SqSolveQuizDto
import com.sw.wordgarden.data.dto.quiz.WqResponseDto
import com.sw.wordgarden.data.dto.quiz.WqStateDto
import com.sw.wordgarden.data.dto.quiz.WqSubmissionDto
import com.sw.wordgarden.data.dto.quiz.WqWrongAnswerDto
import com.sw.wordgarden.data.dto.user.AddRequestFriendDto
import com.sw.wordgarden.data.dto.user.DeleteRequestFriendDto
import com.sw.wordgarden.data.dto.user.FriendListDto
import com.sw.wordgarden.data.dto.user.LoginRequestDto
import com.sw.wordgarden.data.dto.user.ReportInfoDto
import com.sw.wordgarden.data.dto.user.UserDto
import com.sw.wordgarden.data.dto.user.UserInfoDto
import retrofit2.HttpException
import retrofit2.Response
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
            throw e
        }
    }

    override suspend fun updateFcmToken(token: String) {
        try {
            val uid = getUid()
            val payload = mapOf("fcmToken" to token)

            val response = service.updateFcmToken(uid!!, payload)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
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
            throw e
        }
    }

    override suspend fun updateUserNickname(nickname: String) {
        try {
            val uid = getUid()
            val payload = mapOf("nickname" to nickname)

            val response = service.updateUserNickname(uid!!, payload)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun updateUserImage(image: String) {
        try {
            val uid = getUid()
            val payload = mapOf("image" to image)

            val response = service.updateUserImage(uid!!, payload)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getFriends(): FriendListDto? {
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
            throw e
        }
    }

    //user - mypage - friend
    override suspend fun addFriend(friendUrl: String) {
        try {
            val uid = getUid()
            val request = AddRequestFriendDto(uid!!, friendUrl)

            val response = service.addFriend(request)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun deleteFriend(friendUid: String) {
        try {
            val uid = getUid()
            val request = DeleteRequestFriendDto(uid!!, friendUid)

            val response = service.deleteFriend(request)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun reportUser(friendUid: String, contents: String?) {
        try {
            val uid = getUid()
            val reportInfoDto = ReportInfoDto(
                reporterId = uid!!,
                reportedId = friendUid,
                reason = contents
            )

            val response = service.reportUser(reportInfoDto)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    //alarm
    override suspend fun makeSharingAlarm(toUserId: String, quizId: String) {
        try {
            val uid = getUid()
            val shareRequest = ShareRequestDto(
                fromUserId = uid,
                toUserId = toUserId,
                quizId = quizId
            )
            val response = service.makeSharingAlarm(shareRequest)
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getAlarms(): List<AlarmDto>? {
        return try {
            val uid = getUid()

            val response = service.getAlarms(uid!!)
            if (!response.isSuccessful) {
                throw HttpException(response)
            } else {
                response.body()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getAlarmDetail(alarmId: String): AlarmDetailDto? {
        return try {
            val response = service.getAlarmDetail(alarmId)
            if (!response.isSuccessful) {
                throw HttpException(response)
            } else {
                response.body()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun deleteAlarm(alarmId: String) {
        try {
            val uid = getUid()

            val response = service.deleteAlarm(alarmId, uid!!)
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
            throw e
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
            throw e
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
            throw e
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
            throw e
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
            throw e
        }
    }

    override suspend fun getWqOrSolvedWq(title: String, isSolved: Boolean): List<WqResponseDto>? {
        return try {
            val uid: String?
            val response: Response<List<WqResponseDto>>?

            if (isSolved) {
                uid = getUid()
                response = service.getWqOrSolvedWq(title, uid!!)
            } else {
                response = service.getWqOrSolvedWq(title, null)
            }

            if (!response.isSuccessful) {
                throw HttpException(response)
            } else {
                response.body()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    //quiz - sq
    override suspend fun createNewSq(sqDto: SqDto): SqCreatedInfoDto? {
        return try {
            val uid = getUid()
            val quizListData = sqDto.copy(
                uid = uid
            )
            val response = service.createNewSq(quizListData)
            if (!response.isSuccessful) {
                throw HttpException(response)
            } else {
                response.body()
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
            throw e
        }
    }

    override suspend fun getUserSq(creatorUid: String?, quizId: String): SqDto? {
        return try {
            val uid = if (creatorUid.isNullOrEmpty()) {
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
            throw e
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
            throw e
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
            throw e
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
            throw e
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
            throw e
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