package ru.kor.test_it_cron.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.kor.test_it_cron.BuildConfig
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GitHubApiModule {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/vnd.github+json")
                .addHeader("Authorization", BuildConfig.API_KEY)
                .addHeader("X-GitHub-Api-Version", "2022-11-28")
                .build()
            chain.proceed(request)
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideGitHubService(retrofit: Retrofit): GitHubService = retrofit.create()
}