package ru.kor.test_it_cron.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kor.test_it_cron.repository.Repository
import ru.kor.test_it_cron.repository.RepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsRepository(impl: RepositoryImpl): Repository
}