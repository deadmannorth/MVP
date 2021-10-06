package ru.aslazarev.mvp

import android.app.Application
import ru.aslazarev.mvp.model.db.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        Database.create(this)
    }
}