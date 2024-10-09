import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android.feature)
}

android {
    namespace = "com.selfpro.realies.my"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies{
    implementation(projects.util.shared)
    implementation(projects.data.model)
    implementation(projects.util.common)
    implementation(projects.data.network)
    implementation(projects.util.icon)
}
