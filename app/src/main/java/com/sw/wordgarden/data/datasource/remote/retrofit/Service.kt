package com.sw.wordgarden.data.datasource.remote.retrofit

import com.sw.wordgarden.data.dto.quiz.SqQuestionAnswerDto
import com.sw.wordgarden.data.dto.quiz.SqQuizSummaryDto
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
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    //user - login
    @POST("login/login")
    suspend fun insertUser(@Body request: LoginRequestDto): Response<Unit>

    @GET("login/user/{uid}")
    suspend fun getUserInfoForLogin(@Path("uid") uid: String): Response<UserDto>

    @POST("") //TODO: 서버 구현 시 수정
    suspend fun sendFirebaseToken(@Body uid: String, token: String): Response<UserDto>

    //user - mypage
    @GET("user/info/{uid}")
    suspend fun getUserInfoForMypage(@Path("uid") uid: String): Response<UserInfoDto>

    @PUT("user/nickname/{uid}")
    suspend fun updateUserNickname(@Path("uid") uid: String, @Body nickname: String): Response<Unit>

    @GET("user/friends/{uid}")
    suspend fun getFriends(@Path("uid") uid: String): Response<List<UserDto>>

    @POST("user/report")
    suspend fun reportUser(@Body reportInfo: ReportInfoDto): Response<Unit>


    //word
    //    @POST("login/login")
    suspend fun insertLikedWord(@Body word: WordDto): Response<Unit>

    //    @POST("login/login")
    suspend fun deleteLikedWord(@Body wordId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun getLikedWordList(@Body uid: String): Response<List<WordDto>>

    //    @POST("login/login")
    suspend fun getWeeklyWordList(@Body uid: String): Response<List<WordDto>>


    //quiz - wq
    @POST("wq/generate")
    suspend fun getGeneratedWq(): Response<List<WqResponseDto>>

    @POST("wq/submit")
    suspend fun submitWq(@Body wqSubmission: WqSubmissionDto): Response<Unit>

    @GET("wq/stats/{userId}")
    suspend fun getWqState(@Path("userId") uid: String): Response<WqStateDto>

    @GET("wq/wrong/{userId}")
    suspend fun getWrongWqs(@Path("userId") uid: String): Response<List<WqWrongAnswerDto>>

    @GET("wq/title/{userId}")
    suspend fun getSolvedWqTitles(@Path("userId") uid: String): Response<Set<String>>

    @GET("wq/{title}")
    suspend fun getSolvedWq(@Path("title") title: String, @Query("userId") uid: String): Response<List<WqResponseDto>>

    //quiz - sq
    @POST("sq/create")
    suspend fun createNewSq(@Body quizList: SqDto): Response<Unit>

    @GET("sq/created/{uid}")
    suspend fun getUserSqTitles(@Path("uid") uid: String): Response<List<SqQuizSummaryDto>>

    @GET("sq/created/{uid}/{sqid}")
    suspend fun getUserSq(@Path("uid") uid: String, @Path("sqid") quizId: String): Response<SqDto>

    @GET("sq/quiz/{sqid}")
    suspend fun getSq(@Path("sqid") quizId: String): Response<List<SqQuestionAnswerDto>>

    @POST("sq/solve")
    suspend fun submitSq(@Body solvedQuiz: SqSolveQuizDto): Response<Unit>

    @GET("sq/solved/{uid}")
    suspend fun getSolvedSqTitles(@Path("uid") uid: String): Response<List<String>>

    @GET("sq/solved/{uid}/{title}")
    suspend fun getSolvedSq(@Path("uid") uid: String, @Path("title") title: String): Response<SqDto>

    @GET("sq/creator/{sqid}")
    suspend fun getSqCreatorInfo(@Path("sqid") quizId: String): Response<SqCreatorInfoDto>

    @POST("") //TODO: 서버 구현 시 수정
    suspend fun shareQuiz(@Body uid: String, quizTitle: String, friendId: String): Response<Unit>

    //garden
    //    @POST("login/login")
    suspend fun updateTree(@Body treeId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun getTreeList(@Body uid: String): Response<List<TreeDto>>
}