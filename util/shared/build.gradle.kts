import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android.feature)
}


android {
    namespace = "com.selfpro.realies.util.shared"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies{

    implementation(project(":data:model"))
    implementation(project(":data:network"))
    implementation(project(":util:common"))

    //Gemini
    implementation(libs.generativeai)
    implementation(project(":data:domain"))
}