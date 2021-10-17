package ru.aslazarev.mvp.model.db

import io.reactivex.rxjava3.core.Single
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.GitHubUserRepos

class RoomGithubRepositoriesCache(private val db: Database): IRoomCache<GitHubUserRepos> {

    lateinit var user: GitHubUser
    override fun setCache(cache: List<GitHubUserRepos>) {
        val roomUser = db.userDao.getByLogin(user.login.orEmpty()) ?: error("")
        val roomRepos = cache.map {
            RoomGitHubRepository(
                id = it.id!!,
                name = it.name!!,
                forks = it.forks!!,
                userId = roomUser.id
            )
        }
        db.repositoryDao.insert(roomRepos)
    }

    override fun getCache() = Single.fromCallable {
        val roomUser = db.userDao.getByLogin(user.login.orEmpty()) ?: error("")
        db.repositoryDao.getByUserId(roomUser.id).map {
            GitHubUserRepos(it.id, it.name, it.forks)
        }
    }
}