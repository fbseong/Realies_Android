// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false

    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false

    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"

    id("com.google.dagger.hilt.android") version "2.44" apply false
}


