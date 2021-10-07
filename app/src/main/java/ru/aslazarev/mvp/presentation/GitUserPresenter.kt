package ru.aslazarev.mvp.presentation

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.GitHubUsersRepo
import ru.aslazarev.mvp.navigation.AndroidScreens
import ru.aslazarev.mvp.view.ui.GitUserView
import ru.aslazarev.mvp.view.ui.adapter.RepoItemView
import ru.terrakok.cicerone.Router

class GitUserPresenter (val router: Router) : MvpPresenter<GitUserView>() {

    class RepoListPresenter() : IRepoListPresenter{
        val repositories = mutableListOf<GitHubUser.GitHubUserRepos>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            val repo = repositories[view.pos]
            view.setRepo(repo.name!!)
            view.setFork(repo.forks!!)
        }

        override fun getCount()= repositories.size
    }

    val repoListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

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
                repoListPresenter.repositories.addAll(repos)
                viewState.updateList()
            }, {
                Log.e("GitUserPresenter", "Ошибка получения репозиториев", it)
            })
    }



}