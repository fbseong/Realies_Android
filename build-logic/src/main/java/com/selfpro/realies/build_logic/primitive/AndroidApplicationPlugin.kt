package com.selfpro.realies.build_logic.primitive

import com.selfpro.realies.build_logic.dsl.androidApplication
import com.selfpro.realies.build_logic.dsl.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            androidApplication {
                setupAndroid()
            }
        }
    }
}