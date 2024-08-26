package com.selfpro.realies

import android.app.Application

class App : Application() {
    companion object {
        const val BASEURL = "http://10.0.2.2:47893"
        const val naverOpenAIURL = "https://naveropenapi.apigw.ntruss.com"
    }
}