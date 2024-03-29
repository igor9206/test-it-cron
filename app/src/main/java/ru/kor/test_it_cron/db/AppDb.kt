package ru.kor.test_it_cron.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kor.test_it_cron.dao.UserDao
import ru.kor.test_it_cron.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ], version = 1
)
abstract class AppDb:RoomDatabase() {
    abstract fun userDao(): UserDao
}