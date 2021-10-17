package ru.aslazarev.mvp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.aslazarev.mvp.App

@Module
class AppModule(private val app: App) {
    @Provides
    fun app(): Context {
        return app
    }
}