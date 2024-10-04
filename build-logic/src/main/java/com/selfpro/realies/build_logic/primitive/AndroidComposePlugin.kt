package com.selfpro.realies.build_logic.primitive

import com.android.build.api.dsl.LibraryExtension
import com.selfpro.realies.build_logic.dsl.android
import com.selfpro.realies.build_logic.dsl.androidTestImplementation
import com.selfpro.realies.build_logic.dsl.debugImplementation
import com.selfpro.realies.build_logic.dsl.implementation
import com.selfpro.realies.build_logic.dsl.library
import com.selfpro.realies.build_logic.dsl.implementationPlatform
import com.selfpro.realies.build_logic.dsl.libs
import com.selfpro.realies.build_logic.dsl.plugin
import com.selfpro.realies.build_logic.dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            android {
                buildFeatures.compose = true
            }
            dependencies {
                implementation(libs.library("androidx-core-ktx"))

                implementation(libs.library("androidx-appcompat"))
                implementation(libs.library("gson"))

                implementation(libs.library("androidx-lifecycle-runtime-ktx"))
                implementation(libs.library("androidx-compose-activity"))
                implementationPlatform(libs.library("androidx-compose-bom"))
                implementation(libs.library("androidx-compose-ui"))
                implementation(libs.library("androidx-compose-ui-tooling"))
                implementation(libs.library("androidx-compose-navigation"))
                implementation(libs.library("androidx.compose.ui.graphics"))
                implementation(libs.library("androidx-compose-material3"))

                debugImplementation(libs.library("androidx-compose-ui-tooling"))
                debugImplementation(libs.library("androidx-compose-ui-test-manifest"))
                implementation(libs.library("androidx.lifecycle.viewmodel.compose"))

                //Coil
                implementation(libs.library("coil.compose"))
            }
        }
    }
}