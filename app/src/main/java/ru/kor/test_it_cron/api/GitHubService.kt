package ru.kor.test_it_cron.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kor.test_it_cron.dto.User
import ru.kor.test_it_cron.dto.UserInfo

interface GitHubService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users")
    suspend fun getAfterUser(@Query("since") since: Long): Response<List<User>>

    @GET("users/{login}")
    suspend fun getUserByLogin(@Path("login") login: String): Response<UserInfo>

}