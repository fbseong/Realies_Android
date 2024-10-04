plugins {

    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)

    alias(libs.plugins.realies.android.application)
    alias(libs.plugins.realies.android.kotlin)
    alias(libs.plugins.realies.android.compose)
    alias(libs.plugins.realies.android.hilt)

    id("kotlinx-serialization")
}

android {
    namespace = "com.selfpro.realies"

    defaultConfig {
        applicationId = "com.selfpro.realies"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    //Accompanist
    implementation(libs.accompanist.webview)

    //Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.converter.gson)
    implementation(libs.squareup.retrofit.logging.interceptor)


    implementation(libs.generativeai)

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation(libs.gson)
implementation(libs.androidx.appcompat)

    //Module
    implementation(project(":util:common"))
    implementation(project(":data:model"))
    implementation(project(":util:shared"))
    implementation(project(":feature:realies"))
    implementation(project(":feature:my"))
    implementation(project(":feature:add"))
    implementation(project(":feature:search"))
    implementation(project(":feature:challenge"))
    implementation(project(":feature:subscribe"))


//    implementation(libs.accompanist.navigation)

//    implementation(libs.accompanist.pager)
//    implementation(libs.accompanist.swiperefresh)
}
