package ru.aslazarev.mvp.model.remote

import io.reactivex.rxjava3.core.Single
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.db.*
import ru.aslazarev.mvp.remote.ApiHolder
import ru.aslazarev.mvp.utils.INetworkStatus
import javax.inject.Inject

class GitHubUsersRepo @Inject constructor(
    private val networkStatus: INetworkStatus,
    private val cache: RoomGithubUsersCache,
    private val apiHolder: ApiHolder
)
{

    //override
    fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            apiHolder.api.getUsers()
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
