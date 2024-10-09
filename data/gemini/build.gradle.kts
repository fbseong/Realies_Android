import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android)
    alias(libs.plugins.realies.android.kotlin)
    alias(libs.plugins.realies.android.hilt)
    alias(libs.plugins.realies.android.gemini)
}


android {
    namespace = "com.selfpro.realies.data.gemini"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}