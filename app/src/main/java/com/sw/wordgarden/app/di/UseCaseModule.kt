package com.sw.wordgarden.app.di

import com.sw.wordgarden.domain.usecase.datastore.DeleteDailyLimitUseCase
import com.sw.wordgarden.domain.usecase.datastore.DeleteDailyLimitUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.DeleteUidUseCase
import com.sw.wordgarden.domain.usecase.datastore.DeleteUidUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.GetAlarmListUseCase
import com.sw.wordgarden.domain.usecase.datastore.GetAlarmListUseCaseImpl
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
import com.sw.wordgarden.domain.usecase.GetTreeListUseCase
import com.sw.wordgarden.domain.usecase.GetTreeListUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.GetUidUseCase
import com.sw.wordgarden.domain.usecase.datastore.GetUidUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.GetUserInfoForLogin
import com.sw.wordgarden.domain.usecase.user.GetUserInfoForLoginImpl
import com.sw.wordgarden.domain.usecase.GetWeeklyWordListUseCase
import com.sw.wordgarden.domain.usecase.GetWeelkyWordListUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.CreateNewSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.CreateNewSqUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.InsertUserUseCase
import com.sw.wordgarden.domain.usecase.user.InsertUserUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.SaveDailyLimitUseCase
import com.sw.wordgarden.domain.usecase.datastore.SaveDailyLimitUseCaseImpl
import com.sw.wordgarden.domain.usecase.datastore.SaveUidUseCase
import com.sw.wordgarden.domain.usecase.datastore.SaveUidUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.SendFirebaseTokenUseCase
import com.sw.wordgarden.domain.usecase.user.SendFirebaseTokenUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.common.ShareQuizUseCase
import com.sw.wordgarden.domain.usecase.quiz.common.ShareQuizUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.sq.SubmitSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.SubmitSqUseCaseImpl
import com.sw.wordgarden.domain.usecase.UpdateTreeUseCase
import com.sw.wordgarden.domain.usecase.UpdateTreeUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.common.GetSolvedQuizTitlesUseCase
import com.sw.wordgarden.domain.usecase.quiz.common.GetSolvedQuizTitlesUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetGeneratedWqUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetGeneratedWqUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetSolvedWqTitlesUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetSolvedWqTitlesUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetSolvedWqUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetSolvedWqUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWqStateUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWqStateUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWrongWqsUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWrongWqsUseCaseImpl
import com.sw.wordgarden.domain.usecase.quiz.wq.SubmitWqUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.SubmitWqUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.UpdateUserNicknameUseCase
import com.sw.wordgarden.domain.usecase.user.UpdateUserNicknameUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

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
    abstract fun bindGetAlarmListUseCase(
        getAlarmListUseCaseImpl: GetAlarmListUseCaseImpl
    ): GetAlarmListUseCase

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
    abstract fun bindGetUserInfoUseCase(
        getUserInfoUseCaseImpl: GetUserInfoForLoginImpl
    ): GetUserInfoForLogin

    @Binds
    @ViewModelScoped
    abstract fun bindInsertUserUseCase(
        insertUserUseCaseImpl: InsertUserUseCaseImpl
    ): InsertUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSendFirebaseTokenUseCase(
        sendFirebaseTokenUseCaseImpl: SendFirebaseTokenUseCaseImpl
    ): SendFirebaseTokenUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateUserNicknameUseCase(
        updateUserNicknameUseCaseImpl: UpdateUserNicknameUseCaseImpl
    ): UpdateUserNicknameUseCase

    //quiz - common
    @Binds
    @ViewModelScoped
    abstract fun bindGetSolvedQuizTitlesUseCase(
        getSolvedQuizTitlesUseCaseImpl: GetSolvedQuizTitlesUseCaseImpl
    ): GetSolvedQuizTitlesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindShareQuizUseCase(
        shareQuizUseCaseImpl: ShareQuizUseCaseImpl
    ): ShareQuizUseCase

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
    abstract fun bindGetSolvedWqUseCase(
        getSolvedWqUseCaseImpl: GetSolvedWqUseCaseImpl
    ): GetSolvedWqUseCase

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
    abstract fun bindGetTreeListUseCase(
        getTreeListUseCaseImpl: GetTreeListUseCaseImpl
    ): GetTreeListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateTreeUseCase(
        updateTreeUseCaseImpl: UpdateTreeUseCaseImpl
    ): UpdateTreeUseCase

    //word
    @Binds
    @ViewModelScoped
    abstract fun bindGetWordListUseCase(
        getWordListUseCaseImpl: GetWeelkyWordListUseCaseImpl
    ): GetWeeklyWordListUseCase
}