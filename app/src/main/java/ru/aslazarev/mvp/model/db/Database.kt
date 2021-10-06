package ru.aslazarev.mvp.model.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.RuntimeException

@androidx.room.Database(entities = [RoomGitHubUser::class, RoomGitHubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}