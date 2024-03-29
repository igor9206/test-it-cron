package ru.kor.test_it_cron.repository.remotemediator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kor.test_it_cron.api.GitHubService
import ru.kor.test_it_cron.dao.UserDao
import ru.kor.test_it_cron.db.AppDb
import ru.kor.test_it_cron.repository.remotemediator.UserRemoteMediator
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteMediatorModule {

    @Singleton
    @Provides
    fun provideUserRemoteMediator(
        gitHubService: GitHubService,
        appDb: AppDb,
        userDao: UserDao
    ): UserRemoteMediator = UserRemoteMediator(gitHubService, appDb, userDao)
}