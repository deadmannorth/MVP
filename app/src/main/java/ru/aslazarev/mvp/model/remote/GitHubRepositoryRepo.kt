package ru.aslazarev.mvp.model.remote

import io.reactivex.rxjava3.core.Single
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.GitHubUserRepos
import ru.aslazarev.mvp.model.db.Database
import ru.aslazarev.mvp.model.db.RoomGithubRepositoriesCache
import ru.aslazarev.mvp.remote.ApiHolder
import ru.aslazarev.mvp.utils.INetworkStatus

class GitHubRepositoryRepo(
    private val networkStatus: INetworkStatus,
    private val database: Database,
    private val user: GitHubUser,
    private val cache: RoomGithubRepositoriesCache = RoomGithubRepositoriesCache(database, user)
): IGitHubUsersRepo<List<GitHubUserRepos>> {

    override fun getRepository() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            ApiHolder.api.getUserRepo(user.login.orEmpty()).flatMap { repositories ->
                Single.fromCallable {
                    Thread{
                        cache.setCache(repositories)
                    }.start()
                    repositories
                }
            }
        } else {
            cache.getCache()
            }
        }
    }