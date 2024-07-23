package com.sw.wordgarden.data.datasource.remote.Retrofit

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

    //friend
    //    @POST("login/login")
    suspend fun insertFriend(@Body friendId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun deleteFriend(@Body friendId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun reportFriend(@Body friendId: String, contents: String): Response<Unit>

    @GET("/user/friendlist/{uid}")
    suspend fun getFriendList(@Path("uid") uid: String): Response<List<UserDto>>

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

    //garden
    //    @POST("login/login")
    suspend fun updateTree(@Body treeId: String): Response<Unit>

    //    @POST("login/login")
    suspend fun getTreeList(@Body uid: String): Response<List<TreeDto>>
}