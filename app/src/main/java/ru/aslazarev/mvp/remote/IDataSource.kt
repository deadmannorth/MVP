package ru.aslazarev.mvp.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.GitHubUserRepos

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GitHubUser>>

    @GET("/users/{username}/repos")
    fun getUserRepo(@Path("username") username : String): Single<List<GitHubUserRepos>>


}