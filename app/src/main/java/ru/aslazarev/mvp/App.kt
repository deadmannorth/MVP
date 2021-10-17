package ru.aslazarev.mvp

import android.app.Application
import ru.aslazarev.mvp.di.modules.AppComponent
import ru.aslazarev.mvp.di.modules.AppModule
import ru.aslazarev.mvp.di.modules.DaggerAppComponent
import ru.aslazarev.mvp.model.db.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App: Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance: App
    }



    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}