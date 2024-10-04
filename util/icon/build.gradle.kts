import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android)
    alias(libs.plugins.realies.android.kotlin)
    alias(libs.plugins.realies.android.compose)
}

android {
    namespace = "com.selfpro.realies.util.icon"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":util:common"))
//    implementation(libs.androidx.compose.ui)
//    implementation(libs.androidx.compose.ui.tooling)

    //    implementation(project(":util:shared"))
//    implementation(project(":util:common"))
//    implementation(project(":data:network"))
}