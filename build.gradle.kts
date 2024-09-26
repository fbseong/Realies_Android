// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false

    alias(libs.plugins.android.library) apply false
//    id("org.jetbrains.kotlin:kotlin-gradle-plugin") version "1.7.20"

    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false

    //json 파싱
    kotlin("plugin.serialization") version "1.9.0" // Kotlin 버전에 맞는 버전 사용


//    id("com.google.dagger.hilt.android") version "2.44" apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}



