package ru.aslazarev.mvp.model.remote

import io.reactivex.rxjava3.core.Single
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.GitHubUserRepos
import ru.aslazarev.mvp.model.db.Database
import ru.aslazarev.mvp.model.db.RoomGithubRepositoriesCache
import ru.aslazarev.mvp.remote.ApiHolder
import ru.aslazarev.mvp.utils.INetworkStatus
import javax.inject.Inject

class GitHubRepositoryRepo @Inject constructor(
    private val networkStatus: INetworkStatus,
    private val cache: RoomGithubRepositoriesCache,
    private val apiHolder: ApiHolder,
)
{

    fun getRepository(user: GitHubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            apiHolder.api.getUserRepo(user.login.orEmpty()).flatMap { repositories ->
                Single.fromCallable {
                    Thread{
                        cache.user = user
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