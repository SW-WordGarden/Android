package com.sw.wordgarden.app.di

import com.sw.wordgarden.domain.usecase.DeleteFriendUseCase
import com.sw.wordgarden.domain.usecase.DeleteFriendUseCaseImpl
import com.sw.wordgarden.domain.usecase.DeleteQuizListUseCase
import com.sw.wordgarden.domain.usecase.DeleteQuizListUseCaseImpl
import com.sw.wordgarden.domain.usecase.DeleteUserUseCase
import com.sw.wordgarden.domain.usecase.DeleteUserUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetFriendListUseCase
import com.sw.wordgarden.domain.usecase.GetFriendListUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetQuizListByTypeUseCase
import com.sw.wordgarden.domain.usecase.GetQuizListByTypeUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserUseCase
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetQuizeListDoneByUserAndPeriodUseCase
import com.sw.wordgarden.domain.usecase.GetQuizeListDoneByUserAndPeriodUseCaseImpl
import com.sw.wordgarden.domain.usecase.GetTreeListUseCase
import com.sw.wordgarden.domain.usecase.GetTreeListUseCaseImpl
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
import com.sw.wordgarden.domain.usecase.UpdateQuizListUseCase
import com.sw.wordgarden.domain.usecase.UpdateQuizListUseCaseImpl
import com.sw.wordgarden.domain.usecase.UpdateTreeUseCase
import com.sw.wordgarden.domain.usecase.UpdateTreeUseCaseImpl
import com.sw.wordgarden.domain.usecase.UpdateUserUseCase
import com.sw.wordgarden.domain.usecase.UpdateUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
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
    abstract fun bindDeleteUserUseCase(
        deleteUserUseCaseImpl: DeleteUserUseCaseImpl
    ): DeleteUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetFriendListUseCase(
        getFriendListUseCaseImpl: GetFriendListUseCaseImpl
    ): GetFriendListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetQuizeListDoneByUserAndPeriodUseCase(
        getQuizeListDoneByUserAndPeriodUseCaseImpl: GetQuizeListDoneByUserAndPeriodUseCaseImpl
    ): GetQuizeListDoneByUserAndPeriodUseCase

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
    abstract fun bindGetTreeListUseCase(
        getTreeListUseCaseImpl: GetTreeListUseCaseImpl
    ): GetTreeListUseCase

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
    abstract fun bindUpdateQuizListUseCase(
        updateQuizListUseCaseImpl: UpdateQuizListUseCaseImpl
    ): UpdateQuizListUseCase

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