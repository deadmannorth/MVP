package ru.aslazarev.mvp.model.db

import io.reactivex.rxjava3.core.Single
import ru.aslazarev.mvp.model.GitHubUser

class RoomGithubUsersCache(private val db: Database): IRoomCache<GitHubUser> {

    override fun setCache(users: List<GitHubUser>) {
        val roomUsers = users.map { user ->
            RoomGitHubUser(
                user.id.toString(),
                user.login.orEmpty(),
                user.avatarUrl.orEmpty(),
                user.reposUrl.orEmpty()
            )
        }
        db.userDao.insert(roomUsers)
    }

    override fun getCache() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GitHubUser(
                login = roomUser.login,
                id = roomUser.id,
                avatarUrl = roomUser.avatarUrl,
                reposUrl = roomUser.reposUrl,
            )
        }
    }
}