package com.selfpro.realies.data.network.retrofit

import com.selfpro.realies.App
import com.selfpro.realies.data.network.api.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(App.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // NewsAPI 객체를 제공
    @Provides
    @Singleton
    fun provideNewsAPI(retrofit: Retrofit): NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }


//    private val client = OkHttpClient.Builder()
//        .addInterceptor(HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        })
//        .addInterceptor(HeaderInterceptor())
//        .build()


//    @Singleton
//    @Provides
//    fun provideOkHttpClient(): OkHttpClient {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
////            .addConverterFactory(MoshiConverterFactory.create())
//
//            .baseUrl(App.BASEURL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideApiService(retrofit: Retrofit): NewsAPI {
//        return retrofit.create(NewsAPI::class.java)
//    }

//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl(App.BASEURL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    private val naverOpenAIBuilder: Retrofit = Retrofit.Builder()
//        .baseUrl(App.naverOpenAIURL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val newsAPI by lazy { retrofit.create(NewsAPI::class.java) }
//    val naverOpenAI by lazy { naverOpenAIBuilder.create(NaverOpenAIAPI::class.java) }
}