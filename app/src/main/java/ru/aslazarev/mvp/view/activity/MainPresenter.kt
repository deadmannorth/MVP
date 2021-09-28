package ru.aslazarev.mvp.view.activity

import moxy.MvpPresenter
import ru.aslazarev.mvp.navigation.AndroidScreens
import ru.terrakok.cicerone.Router

class MainPresenter (val router: Router) : MvpPresenter<MainView>()
{
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}