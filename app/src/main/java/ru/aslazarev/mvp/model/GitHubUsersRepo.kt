package ru.aslazarev.mvp.model

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable

class GitHubUsersRepo {
    private val repositories = listOf(

        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5")
    )

    fun getUserObserver(): @NonNull Observable<GitHubUser> {
        return Observable.fromIterable(repositories)
    }
}
