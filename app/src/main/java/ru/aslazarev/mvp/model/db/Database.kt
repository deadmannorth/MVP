package ru.aslazarev.mvp.model.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.RuntimeException

@androidx.room.Database(entities = [RoomGitHubUser::class, RoomGitHubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
}