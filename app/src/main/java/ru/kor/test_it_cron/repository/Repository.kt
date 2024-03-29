package ru.kor.test_it_cron.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.kor.test_it_cron.dto.User
import ru.kor.test_it_cron.dto.UserInfo

interface Repository {
    val userData: Flow<PagingData<User>>

    suspend fun getUser(userLogin: String): UserInfo
}