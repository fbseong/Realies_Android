import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android.feature)
//    alias(libs.plugins.realies.android.gemini)
}


android {
    namespace = "com.selfpro.realies.util.shared"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies{

    implementation(projects.data.model)
    implementation(projects.data.network)
    implementation(projects.util.common)

    //Gemini

    implementation(projects.data.domain)
}