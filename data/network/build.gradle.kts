import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android)
    alias(libs.plugins.realies.android.kotlin)
    alias(libs.plugins.realies.android.hilt)
}


android {
    namespace = "com.selfpro.realies.data.network"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies{

    implementation(project(":data:model"))

    //Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.converter.gson)
    implementation(libs.squareup.retrofit.logging.interceptor)
}