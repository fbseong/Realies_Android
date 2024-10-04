import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.b1nd.dodam.buildlogic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "selfpro.realies.primitive.android.application"
            implementationClass = "com.selfpro.realies.build_logic.primitive.AndroidApplicationPlugin"
        }
        register("androidLibrary"){
            id = "selfpro.realies.primitive.android.library"
            implementationClass = "com.selfpro.realies.build_logic.primitive.AndroidLibraryPlugin"
        }
        register("androidHilt") {
            id = "selfpro.realies.primitive.android.hilt"
            implementationClass = "com.selfpro.realies.build_logic.primitive.HiltPlugin"
        }
        register("android") {
            id = "selfpro.realies.primitive.android"
            implementationClass = "com.selfpro.realies.build_logic.primitive.AndroidPlugin"
        }
        register("androidKotlin") {
            id = "selfpro.realies.primitive.android.kotlin"
            implementationClass = "com.selfpro.realies.build_logic.primitive.AndroidKotlinPlugin"
        }
        register("androidCompose") {
            id = "selfpro.realies.primitive.android.compose"
            implementationClass = "com.selfpro.realies.build_logic.primitive.AndroidComposePlugin"
        }


        //Convention
        register("androidFeature") {
            id = "selfpro.realies.convention.android.feature"
            implementationClass = "com.selfpro.realies.build_logic.convention.AndroidFeaturePlugin"
        }

    }
}