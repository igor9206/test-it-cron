package ru.kor.test_it_cron.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.kor.test_it_cron.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Insert
    suspend fun insert(users: List<UserEntity>)

    @Query("SELECT * FROM UserEntity ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM UserEntity")
    suspend fun clearAll()

    @Query("SELECT max(id) FROM UserEntity")
    suspend fun getMaxId(): Long

    @Query("SELECT COUNT(*) == 0 FROM UserEntity")
    suspend fun isEmpty(): Boolean

}