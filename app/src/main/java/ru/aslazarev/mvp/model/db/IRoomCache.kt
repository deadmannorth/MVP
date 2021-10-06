package ru.aslazarev.mvp.model.db

import io.reactivex.rxjava3.core.Single
import ru.aslazarev.mvp.model.GitHubUser

interface IRoomCache<T> {
    fun setCache(cache: List<T>)
    fun getCache(): Single<List<T>>
}