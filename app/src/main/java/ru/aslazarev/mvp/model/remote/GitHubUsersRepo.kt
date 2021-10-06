package ru.aslazarev.mvp.model.remote

import io.reactivex.rxjava3.core.Single
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.db.*
import ru.aslazarev.mvp.remote.ApiHolder
import ru.aslazarev.mvp.utils.INetworkStatus

class GitHubUsersRepo (
    private val networkStatus: INetworkStatus,
    private val cache: RoomGithubUsersCache
): IGitHubUsersRepo<List<GitHubUser>> {

    //override fun getUserRepos(userName: String)= api.getUserRepo(userName).subscribeOn(Schedulers.io())

    override fun getRepository() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            ApiHolder.api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        Thread {
                            cache.setCache(users)
                        }.start()
                        users
                    }
                }
        } else {
            cache.getCache()
        }
    }

}
