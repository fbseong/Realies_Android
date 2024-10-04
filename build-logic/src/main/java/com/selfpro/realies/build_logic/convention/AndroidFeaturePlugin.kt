package com.selfpro.realies.build_logic.convention

import com.selfpro.realies.build_logic.dsl.implementation
import com.selfpro.realies.build_logic.dsl.library
import com.selfpro.realies.build_logic.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("selfpro.realies.primitive.android")
                apply("selfpro.realies.primitive.android.kotlin")
                apply("selfpro.realies.primitive.android.compose")
                apply("selfpro.realies.primitive.android.hilt")
            }
            dependencies {
                implementation(libs.library("orbit.mvi.viewmodel"))
                implementation(libs.library("orbit.mvi.compose"))
            }
        }
    }
}