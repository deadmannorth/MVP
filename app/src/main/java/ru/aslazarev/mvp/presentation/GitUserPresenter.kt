package ru.aslazarev.mvp.presentation

import moxy.MvpPresenter
import ru.aslazarev.mvp.screens.AndroidScreens
import ru.aslazarev.mvp.view.ui.GitUserView
import ru.terrakok.cicerone.Router

class GitUserPresenter (val router: Router) : MvpPresenter<GitUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }

}