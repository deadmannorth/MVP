package ru.aslazarev.mvp.presentation

import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.GitHubUserRepos
import ru.aslazarev.mvp.model.db.Database
import ru.aslazarev.mvp.model.remote.GitHubRepositoryRepo
import ru.aslazarev.mvp.model.remote.GitHubUsersRepo
import ru.aslazarev.mvp.navigation.AndroidScreens
import ru.aslazarev.mvp.utils.AndroidNetworkStatus
import ru.aslazarev.mvp.view.ui.GitUserView
import ru.aslazarev.mvp.view.ui.adapter.RepoItemView
import ru.terrakok.cicerone.Router

class GitUserPresenter (val repo: GitHubRepositoryRepo, val router: Router) : MvpPresenter<GitUserView>() {

    class RepoListPresenter() : IRepoListPresenter{
        val repositories = mutableListOf<GitHubUserRepos>()

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

    fun loadData() {
        repo.getRepository()
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