package com.selfpro.realies.build_logic.primitive

import com.selfpro.realies.build_logic.dsl.android
import com.selfpro.realies.build_logic.dsl.debugImplementation
import com.selfpro.realies.build_logic.dsl.implementation
import com.selfpro.realies.build_logic.dsl.implementationPlatform
import com.selfpro.realies.build_logic.dsl.library
import com.selfpro.realies.build_logic.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidGeminiPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
            }
            android {
                buildFeatures.buildConfig = true
            }
            dependencies {
                implementation(libs.library("generativeai"))
            }
        }
    }
}