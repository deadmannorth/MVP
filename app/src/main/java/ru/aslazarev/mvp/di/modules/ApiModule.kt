package ru.aslazarev.mvp.di.modules

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.aslazarev.mvp.remote.ApiHolder
import ru.aslazarev.mvp.remote.IApiHolder
import ru.aslazarev.mvp.remote.IDataSource
import ru.aslazarev.mvp.utils.AndroidNetworkStatus
import ru.aslazarev.mvp.utils.INetworkStatus
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrl(): String = "https://api.github.com"

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    @Singleton
    fun dataSource(
        gson: Gson,
        @Named("baseUrl") baseUrl: String) : IDataSource {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)
    }

    @Provides
    @Singleton
    fun apiHolder(
        api: IDataSource
    ): ApiHolder {
        return ApiHolder(api = api)
    }

    @Provides
    @Singleton
    fun networkStatus(context: Context): INetworkStatus = AndroidNetworkStatus(context)

}