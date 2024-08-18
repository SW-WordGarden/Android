package com.sw.wordgarden.app.di

import com.sw.wordgarden.domain.usecase.CheckQuizResultUseCase
import com.sw.wordgarden.domain.usecase.CheckQuizResultUseCaseImpl
import com.sw.wordgarden.domain.usecase.DeleteDailyLimitUseCase
import com.sw.wordgarden.domain.usecase.DeleteDailyLimitUseCaseImpl
import com.sw.wordgarden.domain.usecase.DeleteFriendUseCase
import com.sw.wordgarden.domain.usecase.DeleteFriendUseCaseImpl
import com.sw.wordgarden.domain.usecase.DeleteQuizListUseCase
import com.sw.wordgarden.domain.usecase.DeleteQuizListUseCaseImpl
import com.sw.wordgarden.domain.usecase.DeleteUidUseCase
import com.sw.wordgarden.domain.usecase.DeleteUidUseCaseImpl
import com.sw.wordgarden.domain.usecase.DeleteUserUseCase
import com.sw.wordgarden.domain.usecase.DeleteUserUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetDailyLimitUseCase
import com.sw.wordgarden.domain.usecase.GetDailyLimitUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetFriendListUseCase
import com.sw.wordgarden.domain.usecase.GetFriendListUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetQuizBySelfQuizIdUseCase
import com.sw.wordgarden.domain.usecase.GetQuizBySelfQuizIdUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetQuizListAllTypeUseCase
import com.sw.wordgarden.domain.usecase.GetQuizListAllTypeUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetQuizListByTypeUseCase
import com.sw.wordgarden.domain.usecase.GetQuizListByTypeUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserByQuizIdUseCase
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserByQuizIdUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserUseCase
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetQuizListDoneByUserAndPeriodUseCase
import com.sw.wordgarden.domain.usecase.GetQuizListDoneByUserAndPeriodUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetSelfQuizCreatorInfoByQuizIdUseCase
import com.sw.wordgarden.domain.usecase.GetSelfQuizCreatorInfoByQuizIdUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetSolvedSelfQuizResultByUserByTitleUseCase
import com.sw.wordgarden.domain.usecase.GetSolvedSelfQuizResultByUserByTitleUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetSolvedSelfQuizTitleListByUserUseCase
import com.sw.wordgarden.domain.usecase.GetSolvedSelfQuizTitleListByUserUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetTodayQuizUseCase
import com.sw.wordgarden.domain.usecase.GetTodayQuizUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetTreeListUseCase
import com.sw.wordgarden.domain.usecase.GetTreeListUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetUidUseCase
import com.sw.wordgarden.domain.usecase.GetUidUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetUserInfoUseCase
import com.sw.wordgarden.domain.usecase.GetUserInfoUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetWeeklyWordListUseCase
import com.sw.wordgarden.domain.usecase.GetWeelkyWordListUseCaseImpl
import com.sw.wordgarden.domain.usecase.InsertFriendUseCase
import com.sw.wordgarden.domain.usecase.InsertFriendUseCaseImpl
import com.sw.wordgarden.domain.usecase.InsertQuizListUseCase
import com.sw.wordgarden.domain.usecase.InsertQuizListUseCaseImpl
import com.sw.wordgarden.domain.usecase.InsertUserUseCase
import com.sw.wordgarden.domain.usecase.InsertUserUseCaseImpl
import com.sw.wordgarden.domain.usecase.ReportFriendUseCase
import com.sw.wordgarden.domain.usecase.ReportFriendUseCaseImpl
import com.sw.wordgarden.domain.usecase.SaveDailyLimitUseCase
import com.sw.wordgarden.domain.usecase.SaveDailyLimitUseCaseImpl
import com.sw.wordgarden.domain.usecase.SaveUidUseCase
import com.sw.wordgarden.domain.usecase.SaveUidUseCaseImpl
import com.sw.wordgarden.domain.usecase.SendQuizAnswerUseCase
import com.sw.wordgarden.domain.usecase.SendQuizAnswerUseCaseImpl
import com.sw.wordgarden.domain.usecase.SendFirebaseTokenUseCase
import com.sw.wordgarden.domain.usecase.SendFirebaseTokenUseCaseImpl
import com.sw.wordgarden.domain.usecase.ShareQuizUseCase
import com.sw.wordgarden.domain.usecase.ShareQuizUseCaseImpl
import com.sw.wordgarden.domain.usecase.SubmitSelfQuizUseCase
import com.sw.wordgarden.domain.usecase.SubmitSelfQuizUseCaseImpl
import com.sw.wordgarden.domain.usecase.UpdateTreeUseCase
import com.sw.wordgarden.domain.usecase.UpdateTreeUseCaseImpl
import com.sw.wordgarden.domain.usecase.UpdateUserUseCase
import com.sw.wordgarden.domain.usecase.UpdateUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindCheckQuizResultUseCase(
        checkQuizResultUseCaseImpe: CheckQuizResultUseCaseImpl
    ): CheckQuizResultUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteDailyLimitUseCase(
        deleteDailyLimitUseCaseImpl: DeleteDailyLimitUseCaseImpl
    ): DeleteDailyLimitUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteFriendUseCase(
        deleteFriendUseCaseImpl: DeleteFriendUseCaseImpl
    ): DeleteFriendUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteQuizListUseCase(
        deleteQuizListUseCaseImpl: DeleteQuizListUseCaseImpl
    ): DeleteQuizListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteUidUseCase(
        deleteUidUseCaseImpl: DeleteUidUseCaseImpl
    ): DeleteUidUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteUserUseCase(
        deleteUserUseCaseImpl: DeleteUserUseCaseImpl
    ): DeleteUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetDailyLimitUseCase(
        getDailyLimitUseCaseImpl: GetDailyLimitUseCaseImpl
    ): GetDailyLimitUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetFriendListUseCase(
        getFriendListUseCaseImpl: GetFriendListUseCaseImpl
    ): GetFriendListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetQuizBySelfQuizIdUseCase(
        getGetQuizBySelfQuizIdUseCaseImpl: GetQuizBySelfQuizIdUseCaseImpl
    ): GetQuizBySelfQuizIdUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetQuizListDoneByUserAndPeriodUseCase(
        getQuizListDoneByUserAndPeriodUseCaseImpl: GetQuizListDoneByUserAndPeriodUseCaseImpl
    ): GetQuizListDoneByUserAndPeriodUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetQuizListAllTypeUseCase(
        getQuizListAllTypeUseCaseImpl: GetQuizListAllTypeUseCaseImpl
    ): GetQuizListAllTypeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetQuizListByTypeUseCase(
        getQuizListByTypeUseCaseImpl: GetQuizListByTypeUseCaseImpl
    ): GetQuizListByTypeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetQuizListMadeByUserUseCase(
        getQuizListMadeByUserUseCaseImpl: GetQuizListMadeByUserUseCaseImpl
    ): GetQuizListMadeByUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetQuizListMadeByUserByQuizIdUseCase(
        getQuizListMadeByUserByTitleUseCaseImpl: GetQuizListMadeByUserByQuizIdUseCaseImpl
    ): GetQuizListMadeByUserByQuizIdUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSelfQuizCreatorInfoByQuizIdUseCase(
        getSelfQuizCreatorInfoByQuizIdUseCaseImpl: GetSelfQuizCreatorInfoByQuizIdUseCaseImpl
    ): GetSelfQuizCreatorInfoByQuizIdUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSolvedSelfQuizResultByUserByTitleUseCase(
        getSolvedSelfQuizResultByUserByTitleUseCaseImpl: GetSolvedSelfQuizResultByUserByTitleUseCaseImpl
    ): GetSolvedSelfQuizResultByUserByTitleUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSolvedSelfQuizTitleListByUserUseCase(
        getSolvedSelfQuizTitleListByUserUseCaseImpl: GetSolvedSelfQuizTitleListByUserUseCaseImpl
    ): GetSolvedSelfQuizTitleListByUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetTodayQuizUseCase(
        getTodayQuizUseCaseImpl: GetTodayQuizUseCaseImpl
    ): GetTodayQuizUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetTreeListUseCase(
        getTreeListUseCaseImpl: GetTreeListUseCaseImpl
    ): GetTreeListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUidUseCase(
        getUidUseCaseImpl: GetUidUseCaseImpl
    ): GetUidUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserInfoUseCase(
        getUserInfoUseCaseImpl: GetUserInfoUseCaseImpl
    ): GetUserInfoUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetWordListUseCase(
        getWordListUseCaseImpl: GetWeelkyWordListUseCaseImpl
    ): GetWeeklyWordListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindInsertFriendUseCase(
        insertFriendUseCaseImpl: InsertFriendUseCaseImpl
    ): InsertFriendUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindInsertQuizListUseCase(
        insertQuizListUseCaseImpl: InsertQuizListUseCaseImpl
    ): InsertQuizListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindInsertUserUseCase(
        insertUserUseCaseImpl: InsertUserUseCaseImpl
    ): InsertUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindReportFriendUseCase(
        reportFriendUseCaseImpl: ReportFriendUseCaseImpl
    ): ReportFriendUseCase

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

    @Binds
    @ViewModelScoped
    abstract fun bindSendFirebaseTokenUseCase(
        sendFirebaseTokenUseCaseImpl: SendFirebaseTokenUseCaseImpl
    ): SendFirebaseTokenUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSendQuizAnswerUseCase(
        sendQuizAnswerUseCaseImpl: SendQuizAnswerUseCaseImpl
    ): SendQuizAnswerUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindShareQuizUseCase(
        shareQuizUseCaseImpl: ShareQuizUseCaseImpl
    ): ShareQuizUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSubmitSelfQuizUseCase(
        submitSelfQuizUseCaseImpl: SubmitSelfQuizUseCaseImpl
    ): SubmitSelfQuizUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateTreeUseCase(
        updateTreeUseCaseImpl: UpdateTreeUseCaseImpl
    ): UpdateTreeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateUserUseCase(
        updateUserUseCaseImpl: UpdateUserUseCaseImpl
    ): UpdateUserUseCase
}