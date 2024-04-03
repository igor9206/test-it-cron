package ru.kor.test_it_cron.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.kor.test_it_cron.api.GitHubService
import ru.kor.test_it_cron.dao.UserDao
import ru.kor.test_it_cron.dto.User
import ru.kor.test_it_cron.dto.UserInfo
import ru.kor.test_it_cron.entity.toDto
import ru.kor.test_it_cron.repository.remotemediator.UserRemoteMediator
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RepositoryImpl @Inject constructor(
    private val gitHubService: GitHubService,
    private val userDao: UserDao,
    userRemoteMediator: UserRemoteMediator
) : Repository {

    override val userData: Flow<PagingData<User>> = Pager(
        config = PagingConfig(15),
        pagingSourceFactory = { userDao.pagingSource() },
        remoteMediator = userRemoteMediator
    ).flow
        .map { entity ->
            entity.map {
                it.toDto()
            }
        }

    override suspend fun getUser(userLogin: String): UserInfo {
        val response = gitHubService.getUserByLogin(userLogin)

        if (!response.isSuccessful) {
            error(response.code())
        }

        return response.body() ?: error(response.code())
    }


}