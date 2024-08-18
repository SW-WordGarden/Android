package com.sw.wordgarden.data.datasource.remote.retrofit

import com.sw.wordgarden.data.dto.QuizSummaryDto
import com.sw.wordgarden.data.dto.SelfQuizCreatorInfoDto
import com.sw.wordgarden.data.dto.SelfQuizDto
import com.sw.wordgarden.data.dto.SignUpDto
import com.sw.wordgarden.data.dto.SolveQuizDto
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.UserDto
import com.sw.wordgarden.data.dto.WordDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.Date

interface Service {
    //user
    @POST("login/login")
    suspend fun insertUser(@Body request: SignUpDto): Response<Unit>

    @POST("user/userout")
    suspend fun deleteUser(@Path("uid") uid: String): Response<Unit>

    //    @POST("login/login")
    suspend fun updateUser(@Body userDto: UserDto): Response<Unit>

    @GET("login/user/{uid}")
    suspend fun getUserInfo(@Path("uid") uid: String): Response<UserDto>

    @POST("user/token") //TODO: 서버 구현 시 수정
    suspend fun sendFirebaseToken(@Body uid: String, token: String): Response<UserDto>

    //friend
    //    @POST("login/login")
    suspend fun insertFriend(@Body friendId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun deleteFriend(@Body friendId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun reportFriend(@Body friendId: String, contents: String): Response<Unit>

    @GET("user/friendlist/{uid}")
    suspend fun getFriendList(@Path("uid") uid: String): Response<List<UserDto>>

    @POST("sq/share") //TODO: 서버 구현 시 수정
    suspend fun shareQuiz(@Body uid: String, quizTitle: String, friendId: String): Response<Unit>

    //word
    //    @POST("login/login")
    suspend fun insertLikedWord(@Body word: WordDto): Response<Unit>

    //    @POST("login/login")
    suspend fun deleteLikedWord(@Body wordId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun getLikedWordList(@Body uid: String): Response<List<WordDto>>

    //    @POST("login/login")
    suspend fun getWeeklyWordList(@Body uid: String): Response<List<WordDto>>

    //quiz
    @POST("sq/create")
    suspend fun insertQuizList(@Body quizList: SelfQuizDto): Response<Unit>

    //    @POST("login/login")
    suspend fun deleteQuizList(@Body quizListId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun getQuizListByType(@Body type: Boolean): Response<SelfQuizDto>

    //    @POST("login/login")
    suspend fun getQuizListAllType(@Body uid: String): Response<SelfQuizDto>

    @GET("sq/created/{uid}")
    suspend fun getQuizListMadeByUser(@Path("uid") uid: String): Response<List<QuizSummaryDto>>

    @GET("sq/created/{uid}/{sqid}")
    suspend fun getQuizListMadeByUserBySelfQuizId(@Path("uid") uid: String, @Path("sqid") quizId: String): Response<SelfQuizDto>

    @GET("sq/quiz/{sqid}")
    suspend fun getQuizBySelfQuizId(@Path("sqid") quizId: String): Response<SelfQuizDto>

    @POST("sq/solve")
    suspend fun submitSelfQuiz(@Body solvedQuiz: SolveQuizDto): Response<Unit>

    @GET("sq/solved/{uid}")
    suspend fun getSolvedSelfQuizTitleList(@Path("uid") uid: String): Response<List<String>>

    @GET("sq/solved/{uid}/{title}")
    suspend fun getSolvedSelfQuizResult(@Path("uid") uid: String, @Path("title") title: String): Response<SelfQuizDto>

    //    @POST("login/login")
    suspend fun getQuizListDoneByUserAndPeriod(
        @Body startDate: Date,
        endDate: Date
    ): Response<List<SelfQuizDto>>

    @GET("wq/wq") //TODO: 서버 구현 시 수정
    suspend fun getTodayQuiz(@Path("uid") uid: String): Response<SelfQuizDto> //TODO: 서버 구현 시 수정

    @GET("sq/creator/{sqid}")
    suspend fun getSelfQuizCreatorInfo(@Path("sqid") quizId: String): Response<SelfQuizCreatorInfoDto>

    @POST("wq/{wqid}/answer") //TODO: 서버 구현 시 수정
    suspend fun sendQuizAnswer(
        @Body uid: String,
        quizResult: SelfQuizDto
    ): Response<Unit> //TODO: 서버 구현 시 수정

    //garden
    //    @POST("login/login")
    suspend fun updateTree(@Body treeId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun getTreeList(@Body uid: String): Response<List<TreeDto>>
}