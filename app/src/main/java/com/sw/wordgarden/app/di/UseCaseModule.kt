package com.sw.wordgarden.app.di

import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.domain.usecase.datastore.DeleteDailyLimitUseCase
import com.sw.wordgarden.domain.usecase.datastore.DeleteDailyLimitUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.DeleteUidUseCase
import com.sw.wordgarden.domain.usecase.datastore.DeleteUidUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.GetDailyLimitUseCase
import com.sw.wordgarden.domain.usecase.datastore.GetDailyLimitUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.GetFriendsUseCase
import com.sw.wordgarden.domain.usecase.user.GetFriendsUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSqUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.GetUserSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.GetUserSqUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.GetUserSqTitlesUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.GetUserSqTitlesUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSqCreatorInfoUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSqCreatorInfoUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSolvedSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSolvedSqUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSolvedSqTitlesUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSolvedSqTitlesUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.GetUidUseCase
import com.sw.wordgarden.domain.usecase.datastore.GetUidUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.GetUserInfoForLoginUseCase
import com.sw.wordgarden.domain.usecase.user.GetUserInfoForLoginUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.CreateNewSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.CreateNewSqUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.InsertUserUseCase
import com.sw.wordgarden.domain.usecase.user.InsertUserUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.SaveDailyLimitUseCase
import com.sw.wordgarden.domain.usecase.datastore.SaveDailyLimitUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.SaveUidUseCase
import com.sw.wordgarden.domain.usecase.datastore.SaveUidUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.UpdateFcmTokenUseCase
import com.sw.wordgarden.domain.usecase.user.UpdateFcmTokenUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.SubmitSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.SubmitSqUseCaseImpl
import com.sw.wordgarden.domain.usecase.alarm.DeleteAlarmUseCase
import com.sw.wordgarden.domain.usecase.alarm.DeleteAlarmUseCaseImpl
import com.sw.wordgarden.domain.usecase.alarm.GetAlarmDetailUseCase
import com.sw.wordgarden.domain.usecase.alarm.GetAlarmDetailUseCaseImpl
import com.sw.wordgarden.domain.usecase.alarm.GetAlarmsUseCase
import com.sw.wordgarden.domain.usecase.alarm.GetAlarmsUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.common.GetSolvedQuizTitlesUseCase
import com.sw.wordgarden.domain.usecase.quiz.common.GetSolvedQuizTitlesUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetGeneratedWqUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetGeneratedWqUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetSolvedWqTitlesUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetSolvedWqTitlesUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWqOrSolvedWqUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWqOrSolvedWqUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWqStateUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWqStateUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWrongWqsUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWrongWqsUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.SubmitWqUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.SubmitWqUseCaseImpl
import com.sw.wordgarden.domain.usecase.alarm.MakeSharingAlarmUseCase
import com.sw.wordgarden.domain.usecase.alarm.MakeSharingAlarmUseCaseImpl
import com.sw.wordgarden.domain.usecase.garden.GetGrowInfoUseCase
import com.sw.wordgarden.domain.usecase.garden.GetGrowInfoUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.AddFriendUseCase
import com.sw.wordgarden.domain.usecase.user.AddFriendUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.DeleteAccountUseCase
import com.sw.wordgarden.domain.usecase.user.DeleteAccountUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.DeleteFriendUseCase
import com.sw.wordgarden.domain.usecase.user.DeleteFriendUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.GetUserInfoForMypage
import com.sw.wordgarden.domain.usecase.user.GetUserInfoForMypageImpl
import com.sw.wordgarden.domain.usecase.user.ReportFriendUseCase
import com.sw.wordgarden.domain.usecase.user.ReportFriendUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.UpdateUserImageUseCase
import com.sw.wordgarden.domain.usecase.user.UpdateUserImageUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.UpdateUserNicknameUseCase
import com.sw.wordgarden.domain.usecase.user.UpdateUserNicknameUseCaseImpl
import com.sw.wordgarden.domain.usecase.word.GetDetailWordUseCase
import com.sw.wordgarden.domain.usecase.word.GetDetailWordUseCaseImpl
import com.sw.wordgarden.domain.usecase.word.GetRandomWordUseCase
import com.sw.wordgarden.domain.usecase.word.GetRandomWordUseCaseImpl
import com.sw.wordgarden.domain.usecase.word.GetWeeklyCategoryWordListUseCase
import com.sw.wordgarden.domain.usecase.word.GetWeeklyCategoryWordListUseCaseImpl
import com.sw.wordgarden.domain.usecase.word.GetWeeklyWordListUseCase
import com.sw.wordgarden.domain.usecase.word.GetWeeklyWordListUseCaseImpl
import com.sw.wordgarden.domain.usecase.word.GetWordLikedStatusUseCase
import com.sw.wordgarden.domain.usecase.word.GetWordLikedStatusUseCaseImpl
import com.sw.wordgarden.domain.usecase.word.InsertLikedWordUseCase
import com.sw.wordgarden.domain.usecase.word.InsertLikedWordUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    //datastore
    @Binds
    @ViewModelScoped
    abstract fun bindDeleteDailyLimitUseCase(
        deleteDailyLimitUseCaseImpl: DeleteDailyLimitUseCaseImpl
    ): DeleteDailyLimitUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteUidUseCase(
        deleteUidUseCaseImpl: DeleteUidUseCaseImpl
    ): DeleteUidUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetDailyLimitUseCase(
        getDailyLimitUseCaseImpl: GetDailyLimitUseCaseImpl
    ): GetDailyLimitUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUidUseCase(
        getUidUseCaseImpl: GetUidUseCaseImpl
    ): GetUidUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSaveDailyLimitUseCase(
        saveDailyLimitUseCaseImpl: SaveDailyLimitUseCaseImpl
    ): SaveDailyLimitUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSaveUidUseCase(
        saveUidUseCaseImpl: SaveUidUseCaseImpl
    ): SaveUidUseCase

    //user
    @Binds
    @ViewModelScoped
    abstract fun bindGetFriendListUseCase(
        getFriendListUseCaseImpl: GetFriendsUseCaseImpl
    ): GetFriendsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteAccountUseCase(
        deleteAccountUseCaseImpl: DeleteAccountUseCaseImpl
    ): DeleteAccountUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserInfoUseCase(
        getUserInfoUseCaseImpl: GetUserInfoForLoginUseCaseImpl
    ): GetUserInfoForLoginUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserInfoForMypage(
        getUserInfoForMypageImpl: GetUserInfoForMypageImpl
    ): GetUserInfoForMypage

    @Binds
    @ViewModelScoped
    abstract fun bindInsertUserUseCase(
        insertUserUseCaseImpl: InsertUserUseCaseImpl
    ): InsertUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateFcmTokenUseCase(
        updateFcmTokenUseCaseImpl: UpdateFcmTokenUseCaseImpl
    ): UpdateFcmTokenUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateUserNicknameUseCase(
        updateUserNicknameUseCaseImpl: UpdateUserNicknameUseCaseImpl
    ): UpdateUserNicknameUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateUserImageUseCase(
        updateUserImageUseCaseImpl: UpdateUserImageUseCaseImpl
    ): UpdateUserImageUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAddFriendUseCase(
        addFriendUseCaseImpl: AddFriendUseCaseImpl
    ): AddFriendUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteFriendUseCase(
        deleteFriendUseCaseImpl: DeleteFriendUseCaseImpl
    ): DeleteFriendUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindReportFriendUseCase(
        reportFriendUseCaseImpl: ReportFriendUseCaseImpl
    ): ReportFriendUseCase

    //alarm
    @Binds
    @ViewModelScoped
    abstract fun bindMakeSharingAlarmUseCase(
        makeShardingAlarmUseCaseImpl: MakeSharingAlarmUseCaseImpl
    ): MakeSharingAlarmUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAlarmsUseCase(
        getAlarmsUseCaseImpl: GetAlarmsUseCaseImpl
    ): GetAlarmsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAlarmDetailUseCase(
        getAlarmDetailUseCaseImpl: GetAlarmDetailUseCaseImpl
    ): GetAlarmDetailUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteAlarmUseCase(
        deleteAlarmUseCaseImpl: DeleteAlarmUseCaseImpl
    ): DeleteAlarmUseCase

    //quiz - common
    @Binds
    @ViewModelScoped
    abstract fun bindGetSolvedQuizTitlesUseCase(
        getSolvedQuizTitlesUseCaseImpl: GetSolvedQuizTitlesUseCaseImpl
    ): GetSolvedQuizTitlesUseCase

    //quiz - sq
    @Binds
    @ViewModelScoped
    abstract fun bindCreateNewSqUseCase(
        createNewSqUseCaseImpl: CreateNewSqUseCaseImpl
    ): CreateNewSqUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSolvedSqTitlesUseCase(
        getSolvedSqTitlesUseCaseImpl: GetSolvedSqTitlesUseCaseImpl
    ): GetSolvedSqTitlesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSolvedSqUseCaseUseCase(
        getSolvedSqUseCaseImpl: GetSolvedSqUseCaseImpl
    ): GetSolvedSqUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSqCreatorInfoUseCase(
        getSqCreatorInfoUseCaseImpl: GetSqCreatorInfoUseCaseImpl
    ): GetSqCreatorInfoUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSqUseCase(
        getSqUseCaseImpl: GetSqUseCaseImpl
    ): GetSqUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserSqTitlesUseCase(
        getUserSqTitlesUseCaseImpl: GetUserSqTitlesUseCaseImpl
    ): GetUserSqTitlesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserSqUseCase(
        getUserSqUseCaseImpl: GetUserSqUseCaseImpl
    ): GetUserSqUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSubmitSqUseCase(
        submitSqUseCaseImpl: SubmitSqUseCaseImpl
    ): SubmitSqUseCase

    //quiz - wq
    @Binds
    @ViewModelScoped
    abstract fun bindGetGeneratedWqUseCase(
        getGeneratedWqUseCaseImpl: GetGeneratedWqUseCaseImpl
    ): GetGeneratedWqUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSolvedWqTitles(
        getSolvedWqTitlesImpl: GetSolvedWqTitlesUseCaseImpl
    ): GetSolvedWqTitlesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetWqOrSolvedWqUseCase(
        getWqOrSolvedWqUseCaseImpl: GetWqOrSolvedWqUseCaseImpl
    ): GetWqOrSolvedWqUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetWqStateUseCase(
        getWqStateUseCaseImpl: GetWqStateUseCaseImpl
    ): GetWqStateUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetWrongWqsUseCase(
        getWrongWqsUseCaseImpl: GetWrongWqsUseCaseImpl
    ): GetWrongWqsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSubmitWqUseCase(
        submitWqUseCaseImpl: SubmitWqUseCaseImpl
    ): SubmitWqUseCase


    //garden
    @Binds
    @ViewModelScoped
    abstract fun bindGetGrowInfoUseCase(
        getGrowInfoUseCaseImpl: GetGrowInfoUseCaseImpl
    ): GetGrowInfoUseCase

    //word
    @Binds
    @ViewModelScoped
    abstract fun bindGetDetailWordUseCase(
        getDetailWordUseCaseImpl: GetDetailWordUseCaseImpl
    ): GetDetailWordUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetWeeklyCategoryWordListUseCase(
        getWeeklyCategoryWordListUseCaseImpl: GetWeeklyCategoryWordListUseCaseImpl
    ): GetWeeklyCategoryWordListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetWeeklyWordListUseCase(
        getWeeklyWordListUseCaseImpl: GetWeeklyWordListUseCaseImpl
    ): GetWeeklyWordListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetWordLikedStatusUseCase(
        getWordLikedStatusUseCaseImpl: GetWordLikedStatusUseCaseImpl
    ):GetWordLikedStatusUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindInsertLikedWordUseCase(
        insertLikedWordUseCaseImpl: InsertLikedWordUseCaseImpl
    ):InsertLikedWordUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetRandomWord(
        getRandomWordUseCaseImpl: GetRandomWordUseCaseImpl
    ): GetRandomWordUseCase
}

