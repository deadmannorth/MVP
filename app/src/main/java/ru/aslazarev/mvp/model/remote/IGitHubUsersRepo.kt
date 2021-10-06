package ru.aslazarev.mvp.model.remote

import io.reactivex.rxjava3.core.Single
import ru.aslazarev.mvp.model.GitHubUser

interface IGitHubUsersRepo<T> {
    fun getRepository(): Single<T>
}