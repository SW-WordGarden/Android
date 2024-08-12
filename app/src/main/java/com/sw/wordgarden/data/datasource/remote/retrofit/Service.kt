package com.sw.wordgarden.data.datasource.remote.retrofit

import com.sw.wordgarden.data.dto.QuizListDto
import com.sw.wordgarden.data.dto.SignUpDto
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
    suspend fun insertQuizList(@Body quizList: QuizListDto): Response<Unit>

    //    @POST("login/login")
    suspend fun deleteQuizList(@Body quizListId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun getQuizListByType(@Body type: Boolean): Response<QuizListDto>

    //    @POST("login/login")
    suspend fun getQuizListAllType(@Body uid: String): Response<QuizListDto>

    //    @POST("login/login")
    suspend fun getQuizListMadeByUser(@Body uid: String): Response<List<QuizListDto>>

    //    @POST("login/login")
    suspend fun getQuizListDoneByUserAndPeriod(
        @Body startDate: Date,
        endDate: Date
    ): Response<List<QuizListDto>>

    @GET("wq/wq") //TODO: 서버 구현 시 수정
    suspend fun getTodayQuiz(@Path("uid") uid: String): Response<QuizListDto> //TODO: 서버 구현 시 수정

    @POST("wq/{wqid}/answer") //TODO: 서버 구현 시 수정
    suspend fun sendQuizAnswer(@Body uid: String, quizResult: QuizListDto): Response<Unit> //TODO: 서버 구현 시 수정

    //garden
    //    @POST("login/login")
    suspend fun updateTree(@Body treeId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun getTreeList(@Body uid: String): Response<List<TreeDto>>
}