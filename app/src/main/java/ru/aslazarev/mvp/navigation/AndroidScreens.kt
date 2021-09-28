package ru.aslazarev.mvp.navigation

import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.view.ui.GitUserFragment
import ru.aslazarev.mvp.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() = UsersFragment()
    }

    class UserScreen(private val user: GitHubUser): SupportAppScreen () {

        override fun getFragment() = GitUserFragment.newInstance(user)
    }
}