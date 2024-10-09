import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android.library)
    alias(libs.plugins.realies.android.hilt)
}

android {
    namespace = "com.selfpro.realies.data.domain"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies{
    implementation(projects.data.model)
    implementation(projects.data.network)
    implementation(project(":data:gemini"))
}
