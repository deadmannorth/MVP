package ru.aslazarev.mvp.presentation

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.aslazarev.mvp.model.remote.GitHubUsersRepo
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.navigation.AndroidScreens
import ru.aslazarev.mvp.view.ui.adapter.UserItemView
import ru.aslazarev.mvp.view.ui.UsersView
import ru.terrakok.cicerone.Router

class UsersPresenter(val usersRepo: GitHubUsersRepo, val router: Router) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login!!)
            view.loadAvatar(user.avatarUrl!!)
        }

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val screen = AndroidScreens.UserScreen(usersListPresenter.users[itemView.pos])
                router.navigateTo(screen)
        }
    }

    fun loadData() {
        usersRepo.getRepository()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {Log.e("UsersPresenter", "Ошибка получения пользователей", it)
            })
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
