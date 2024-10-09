plugins {
    alias(libs.plugins.realies.android.application)
    alias(libs.plugins.realies.android.kotlin)
    alias(libs.plugins.realies.android.compose)
    alias(libs.plugins.realies.android.hilt)
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
    //Module
    implementation(projects.util.common)
    implementation(projects.data.model)
    implementation(projects.util.shared)
    implementation(projects.feature.realies)
    implementation(projects.feature.my)
    implementation(projects.feature.add)
    implementation(projects.feature.search)
    implementation(projects.feature.challenge)
    implementation(projects.feature.subscribe)
    implementation(projects.feature.viewer)
    implementation(projects.feature.sign)
}
