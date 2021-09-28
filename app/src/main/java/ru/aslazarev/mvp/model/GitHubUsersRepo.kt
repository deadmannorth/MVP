package ru.aslazarev.mvp.model

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.aslazarev.mvp.remote.ApiHolder.api

class GitHubUsersRepo: IGitHubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUserRepos(userName: String)= api.getUserRepo(userName).subscribeOn(Schedulers.io())
}
