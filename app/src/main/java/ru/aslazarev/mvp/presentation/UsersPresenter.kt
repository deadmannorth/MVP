package ru.aslazarev.mvp.presentation

import moxy.MvpPresenter
import ru.aslazarev.mvp.model.GitHubUsersRepo
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.screens.AndroidScreens
import ru.aslazarev.mvp.view.UserItemView
import ru.aslazarev.mvp.view.ui.UsersView
import ru.terrakok.cicerone.Router

class UsersPresenter(val usersRepo: GitHubUsersRepo, val router: Router) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
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
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
