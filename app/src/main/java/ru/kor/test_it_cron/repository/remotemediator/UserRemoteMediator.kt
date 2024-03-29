package ru.kor.test_it_cron.repository.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import ru.kor.test_it_cron.api.GitHubService
import ru.kor.test_it_cron.dao.UserDao
import ru.kor.test_it_cron.db.AppDb
import ru.kor.test_it_cron.entity.UserEntity
import ru.kor.test_it_cron.entity.toEntity
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(
    private val gitHubService: GitHubService,
    private val appDb: AppDb,
    private val userDao: UserDao
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {

        return try {

            val response = when (loadType) {
                LoadType.REFRESH -> {
                    println("refresh")
                    gitHubService.getUsers()
                }

                LoadType.APPEND -> {
                    println("append")
                    gitHubService.getAfterUser(userDao.getMaxId())
                }

                LoadType.PREPEND -> null
            }

            return if (response != null) {
                val body = response.body() ?: error(response.code())
                appDb.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        userDao.clearAll()
                    }
                    userDao.insertAll(body.toEntity())
                }
                MediatorResult.Success(endOfPaginationReached = body.isEmpty())
            } else {
                MediatorResult.Success(endOfPaginationReached = false)
            }

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }
}