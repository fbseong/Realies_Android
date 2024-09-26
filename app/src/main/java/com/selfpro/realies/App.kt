package com.selfpro.realies

import android.app.Application
import com.google.ai.client.generativeai.type.Content
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
//        const val BASEURL = "http://10.0.2.2:47893"
                const val BASEURL = "http://172.30.1.45:47893"
        const val naverOpenAIURL = "https://naveropenapi.apigw.ntruss.com"

        const val CONTENTCODEText = ""
        const val CONTENTCODEImage = "++\\2420fwelvmewnfw98320^^"
        const val CONTENTCODEAIContent = "++\\2420fwelwfwea9832320^^"
        const val CONTENTCODEMiniTItle = "++\\2420wfwi239f9-21r2f8320^^"
    }
}