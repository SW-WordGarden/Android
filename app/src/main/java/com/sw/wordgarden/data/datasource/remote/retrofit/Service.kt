package com.sw.wordgarden.data.datasource.remote.retrofit

import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.data.dto.alarm.AlarmDetailDto
import com.sw.wordgarden.data.dto.alarm.AlarmDto
import com.sw.wordgarden.data.dto.alarm.ShareRequestDto
import com.sw.wordgarden.data.dto.quiz.OneQuizDto
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
import com.sw.wordgarden.data.dto.user.UserResourceDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    //user - login
    @POST("login/login")
    suspend fun insertUser(@Body request: LoginRequestDto): Response<Unit>

    @GET("login/user/{uid}")
    suspend fun getUserInfoForLogin(@Path("uid") uid: String): Response<UserDto>

    @PATCH("user/fcmtoken/{uid}")
    suspend fun updateFcmToken(
        @Path("uid") uid: String,
        @Body payload: Map<String, String>
    ): Response<Unit>

    //user - mypage
    @GET("user/info/{uid}")
    suspend fun getUserInfoForMypage(@Path("uid") uid: String): Response<UserInfoDto>

    @PATCH("user/nickname/{uid}")
    suspend fun updateUserNickname(
        @Path("uid") uid: String,
        @Body payload: Map<String, String>
    ): Response<Unit>

    @PATCH("user/image/{uid}")
    suspend fun updateUserImage(
        @Path("uid") uid: String,
        @Body payload: Map<String, String>
    ): Response<Unit>

    @GET("user/friends/{uid}")
    suspend fun getFriends(@Path("uid") uid: String): Response<FriendListDto>

    @DELETE("user/delete/{uid}")
    suspend fun deleteAccount(@Path("uid") uid: String): Response<Unit>

    //user - mypage - friend
    @POST("user/friend/add")
    suspend fun addFriend(@Body friend: AddRequestFriendDto): Response<Unit>

    @HTTP(method = "DELETE", path = "user/friend/delete", hasBody = true)
    suspend fun deleteFriend(@Body friend: DeleteRequestFriendDto): Response<Unit>

    @POST("user/report")
    suspend fun reportUser(@Body reportInfo: ReportInfoDto): Response<Unit>

    //alarm
    @POST("share/quiz")
    suspend fun makeSharingAlarm(@Body shareRequest: ShareRequestDto): Response<Unit>

    @GET("share/alarms/{userId}")
    suspend fun getAlarms(@Path("userId") uid: String): Response<List<AlarmDto>>

    @GET("share/alarmdetail/{alarmId}")
    suspend fun getAlarmDetail(@Path("alarmId") alarmId: String): Response<AlarmDetailDto>

    @DELETE("share/alarmdelete/{alarmId}")
    suspend fun deleteAlarm(
        @Path("alarmId") alarmId: String,
        @Query("userId") uid: String,
    ): Response<Unit>

    //word
    @GET("word/learning/{category}")
    suspend fun getWeeklyCategoryWordList(@Path("category") category: String): Response<List<WordDto>>

    @GET("word/words/{wordId}")
    suspend fun getDetailWord(@Path("wordId") wordId : String) : Response<WordDto>

    @POST("like/toggle/{uid}/{wordId}")
    suspend fun insertLikedWord(@Path("uid") uid: String, @Path("wordId") wordId: String, @Body isLiked:Boolean) :Response<Unit>

    @GET("like/status/{uid}/{wordId}")
    suspend fun getWordLikedStatus(@Path("uid") uid: String, @Path("wordId") wordId: String):Response<Boolean>

    @GET("word/learning")
    suspend fun getWeeklyWordList() : Response<List<WordDto>>

    @GET("word/learning/random")
    suspend fun getRandomWord() : Response<WordDto>


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
    suspend fun getWqOrSolvedWq(
        @Path("title") title: String,
        @Query("userId") uid: String?
    ): Response<List<WqResponseDto>>

    //quiz - sq
    @POST("sq/create")
    suspend fun createNewSq(@Body quizList: SqDto): Response<SqCreatedInfoDto>

    @GET("sq/created/{uid}")
    suspend fun getUserSqTitles(@Path("uid") uid: String): Response<List<QuizSummaryDto>>

    @GET("sq/created/{uid}/{sqid}")
    suspend fun getUserSq(@Path("uid") uid: String, @Path("sqid") quizId: String): Response<SqDto>

    @GET("sq/quiz/{sqid}")
    suspend fun getSq(@Path("sqid") quizId: String): Response<List<SqQuestionAnswerDto>>

    @POST("sq/solve")
    suspend fun submitSq(@Body solvedQuiz: SqSolveQuizDto): Response<Unit>

    @GET("sq/solved/{uid}")
    suspend fun getSolvedSqTitles(@Path("uid") uid: String): Response<List<QuizSummaryDto>>

    @GET("sq/solved/{uid}/{title}")
    suspend fun getSolvedSq(@Path("uid") uid: String, @Path("title") title: String): Response<SqDto>

    @GET("sq/creator/{sqid}")
    suspend fun getSqCreatorInfo(@Path("sqid") quizId: String): Response<SqCreatorInfoDto>

    //garden
    @GET("garden/grow/{userId}")
    suspend fun getGrowInfo(@Path("userId") userId:String) : Response<TreeDto>

    @GET("garden/user/{userId}")
    suspend fun getUserResource(@Path("userId") userId:String) : Response<UserResourceDto>

    @POST("garden/buy/{uid}")
    suspend fun buyWateringCans(@Path("uid") userId:String) : Response<Unit>

    @POST("garden/{uid}/watertree")
    suspend fun useWateringCans(@Path("userId") userId:String) : Response<Unit>


    @GET("onequiz/generate/learning?uid={uid}")
    suspend fun getLockQuiz(@Path("uid")uid : String) : Response<OneQuizDto>
}