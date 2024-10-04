package com.selfpro.realies.data.network.retrofit

import okhttp3.Interceptor
import okhttp3.Response

//class HeaderInterceptor: Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val original = chain.request()
//        val response = chain.proceed(original)
//
//        return response
//    }
//}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        // 필요한 헤더 추가 예시
        val request = original.newBuilder()
//            .header("Authorization", "Bearer your_token_here")
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)
    }
}