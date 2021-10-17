package ru.aslazarev.mvp.di.modules

import dagger.Component
import ru.aslazarev.mvp.App
import ru.aslazarev.mvp.presentation.GitUserPresenter
import ru.aslazarev.mvp.presentation.UsersPresenter
import ru.aslazarev.mvp.view.activity.MainActivity
import ru.aslazarev.mvp.view.activity.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    CiceroneModule::class,
    CacheModule::class,
    ApiModule::class
])
interface AppComponent {

    fun presenter(): MainPresenter

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(gitUserPresenter: GitUserPresenter)
}