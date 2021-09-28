package ru.aslazarev.mvp.model

import io.reactivex.rxjava3.core.Single

interface IGitHubUsersRepo {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUserRepos(userName: String): Single<List<GitHubUser.GitHubUserRepos>>
}