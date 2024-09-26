package com.selfpro.realies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        //        const val BASEURL = "http://10.0.2.2:47893"
        const val BASEURL = "http://10.80.163.14:47893"
        const val naverOpenAIURL = "https://naveropenapi.apigw.ntruss.com"

        const val CONTENTCODEText = ""
        const val CONTENTCODEImage = "++\\2420fwelvmewnfw98320^^"
        const val CONTENTCODEAIContent = "++\\2420fwelwfwea9832320^^"
        const val CONTENTCODEMiniTItle = "++\\2420wfwi239f9-21r2f8320^^"
    }
}