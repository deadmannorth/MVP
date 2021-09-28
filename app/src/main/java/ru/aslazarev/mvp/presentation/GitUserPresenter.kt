package ru.aslazarev.mvp.presentation

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.GitHubUsersRepo
import ru.aslazarev.mvp.navigation.AndroidScreens
import ru.aslazarev.mvp.view.ui.GitUserView
import ru.terrakok.cicerone.Router

class GitUserPresenter (val router: Router) : MvpPresenter<GitUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.initRepos()
        viewState.updateList()

    }

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }

    fun loadData(user: GitHubUser) {
        GitHubUsersRepo().getUserRepos(user.login!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                user.ReposList = repos as MutableList<GitHubUser.GitHubUserRepos>?
            }, {
                Log.e("GitUserPresenter", "Ошибка получения репозиториев", it)
            })
    }

}