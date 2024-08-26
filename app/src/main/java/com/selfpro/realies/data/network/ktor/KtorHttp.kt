package com.selfpro.realies.data.network.ktor

import com.selfpro.realies.util.SpLog
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

//object KtorHttp {
//    val client = HttpClient(CIO) {
//        install(ContentNegotiation) {
//            json(Json {
//                ignoreUnknownKeys = true
//                isLenient = true
//            })
//        }
//        install(Logging) {
//            level = LogLevel.ALL
////            logger = object : Logger {
////                override fun log(message: String) {
////                    SpLog.d(message)
////                }
////            }
//        }
//        install(DefaultRequest) {
//            header("X-NCP-APIGW-API-KEY-ID", "p3mb4j7znj")
//            header("X-NCP-APIGW-API-KEY", "W6QfzbUk3G0KewBY3mPaeyMWvognN25wu08XMEC6")
//            header("Content-Type", "application/json")
//        }
//    }
//}