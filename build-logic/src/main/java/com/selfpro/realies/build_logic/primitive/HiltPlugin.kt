package com.selfpro.realies.build_logic.primitive

import com.selfpro.realies.build_logic.dsl.android
import com.selfpro.realies.build_logic.dsl.implementation
import com.selfpro.realies.build_logic.dsl.ksp
import com.selfpro.realies.build_logic.dsl.library
import com.selfpro.realies.build_logic.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("com.google.devtools.ksp")
            }
            android {
                packagingOptions {
                    resources {
                        excludes += "META-INF/gradle/incremental.annotation.processors"
                    }
                }
            }
            dependencies {
                implementation(libs.library("hilt-android"))
                ksp(libs.library("hilt-android-compiler"))
                implementation(libs.library("androidx-compose-hilt-navigation"))
            }
        }
    }
}