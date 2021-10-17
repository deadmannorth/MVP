package ru.aslazarev.mvp.presentation

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.GitHubUserRepos
import ru.aslazarev.mvp.model.remote.GitHubRepositoryRepo
import ru.aslazarev.mvp.navigation.AndroidScreens
import ru.aslazarev.mvp.view.ui.GitUserView
import ru.aslazarev.mvp.view.ui.adapter.RepoItemView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class GitUserPresenter(private val user: GitHubUser) : MvpPresenter<GitUserView>() {

    @Inject lateinit var repo: GitHubRepositoryRepo

    @Inject lateinit var router: Router

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
        repo.getRepository(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                repoListPresenter.repositories.clear()
                repoListPresenter.repositories.addAll(repos)
                viewState.updateList()
            }, {
                Log.e("GitUserPresenter", "Ошибка получения репозиториев", it)
            })
    }



}