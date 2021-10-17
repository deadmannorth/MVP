package ru.aslazarev.mvp.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.db.Database
import ru.aslazarev.mvp.model.db.RoomGithubRepositoriesCache
import ru.aslazarev.mvp.model.db.RoomGithubUsersCache
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun db(context: Context): Database {
        return Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    companion object {
        private const val DB_NAME = "database.db"
    }

    @Provides
    fun userCache(db: Database): RoomGithubUsersCache {
        return RoomGithubUsersCache(db)
    }

    @Provides
    fun repoCache(db: Database): RoomGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(db)
    }
}