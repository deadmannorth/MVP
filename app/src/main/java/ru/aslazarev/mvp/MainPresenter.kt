package ru.aslazarev.mvp

import moxy.MvpPresenter
import ru.aslazarev.mvp.model.GitHubUsersRepo
import ru.aslazarev.mvp.model.GithubUser
import ru.aslazarev.mvp.presentation.IUserListPresenter
import ru.aslazarev.mvp.view.UserItemView

class MainPresenter (val usersRepo: GitHubUsersRepo) : MvpPresenter<MainView> (){

    class UserListPresenter: IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenter.itemClickListener = {
            //TODO: переход на экран пользователя
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        userListPresenter.users.addAll(users)
        viewState.updateList()
    }
}