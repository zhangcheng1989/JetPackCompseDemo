package com.example.myapplication.coroutine.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


data class User(val name: String, val address: String)

val userServiceApi: UserServiceApi by lazy {
    val retrofit = retrofit2.Retrofit.Builder()
        .client(OkHttpClient().newBuilder().addInterceptor {
            it.proceed(it.request().apply {

            })
        }.build()).baseUrl("").addConverterFactory(MoshiConverterFactory.create()).build()

    retrofit.create(UserServiceApi::class.java)
}

interface UserServiceApi {
    @GET("user")
    fun loadUser(@Query(value = "name") name:String):Call<User>
}