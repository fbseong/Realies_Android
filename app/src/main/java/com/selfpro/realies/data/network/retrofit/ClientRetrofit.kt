package com.selfpro.realies.data.network.retrofit

import com.selfpro.realies.App
import com.selfpro.realies.data.network.api.NewsAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientRetrofit {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(HeaderInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(App.BASEURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsAPI by lazy { retrofit.create(NewsAPI::class.java) }
}