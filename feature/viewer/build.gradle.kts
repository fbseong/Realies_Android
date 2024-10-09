import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android.feature)
}

android {
    namespace = "com.selfpro.realies.viewer"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.util.icon)
    implementation(projects.util.common)
    implementation(projects.feature.common)
    implementation(projects.data.network)
    implementation(projects.util.shared)
    implementation(projects.data.model)

    implementation(libs.accompanist.webview)
}