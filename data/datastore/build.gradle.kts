import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android.library)
    alias(libs.plugins.realies.android.hilt)
    alias(libs.plugins.protobuf)
}


android {
    namespace = "com.selfpro.realies.data.datastore"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(projects.data.model)
    implementation(projects.data.network)
    implementation(project(":util:common"))

    implementation(libs.androidx.datastore)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.protobuf.kotlin.lite)
}
