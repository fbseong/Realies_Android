package com.selfpro.realies.data.network.ktor

import android.util.Log
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.data.network.request.NaverSummarizeRequest
import com.selfpro.realies.data.network.response.NaverSummarizeResponse
import com.selfpro.realies.util.SpLog
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

//class ClientKtor {
//    companion object {
//        suspend fun summarize(title: String, content: String): LoadState {
//            return try {
//                val data = NaverSummarizeRequest(
//                    NaverSummarizeRequest.Document(
//                        title = title,
//                        content = content
//                    ),
//                    NaverSummarizeRequest.Option(
//                        language = "ko",
//                        model = "news",
//                        tone = 3,
//                        summaryCount = 1
//                    )
//                )
//                val response =
//                    KtorHttp.client.post("https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize") {
//                        contentType(ContentType.Application.Json)
//                        setBody(data)
//                    }.body<NaverSummarizeResponse>()
//
//                if (response.summary.isEmpty()) {
//                    LoadState.Error
//                } else {
//                    LoadState.Success(response.summary)
//                }
//            } catch (e: Exception) {
//                LoadState.Error
//            }
//        }
//
//        fun close() = KtorHttp.client.close()
//    }
//}