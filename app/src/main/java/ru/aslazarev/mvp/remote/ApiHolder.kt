package ru.aslazarev.mvp.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface IApiHolder{
    val api: IDataSource
}

class ApiHolder @Inject constructor(override val api: IDataSource ): IApiHolder