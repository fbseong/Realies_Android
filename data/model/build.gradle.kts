import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android.library)
    alias(libs.plugins.realies.android.compose)

}

android {
    namespace = "com.selfpro.realies.data.model"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

