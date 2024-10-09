import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android)
    alias(libs.plugins.realies.android.kotlin)
    alias(libs.plugins.realies.android.hilt)
}


android {
    namespace = "com.selfpro.realies.util.common"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies{}