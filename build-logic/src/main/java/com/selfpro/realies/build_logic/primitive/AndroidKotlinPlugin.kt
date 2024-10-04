package com.selfpro.realies.build_logic.primitive

import com.selfpro.realies.build_logic.dsl.android
import com.selfpro.realies.build_logic.dsl.androidKotlin
import com.selfpro.realies.build_logic.dsl.implementation
import com.selfpro.realies.build_logic.dsl.library
import com.selfpro.realies.build_logic.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            tasks.withType(KotlinCompile::class.java) {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }

            android {
                androidKotlin {
                    compilerOptions {
                        freeCompilerArgs.addAll(
                            listOf(
                                "-opt-in=kotlin.RequiresOptIn",
                                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                                "-Xcontext-receivers"
                            )
                        )
                    }
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                    }
                }
            }

            dependencies {
                implementation(libs.library("kotlinx-coroutines-core"))
                implementation(libs.library("kotlinx-collections-immutable"))
            }
        }
    }
}