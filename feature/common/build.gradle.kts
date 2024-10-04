import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android.feature)
}

android {
    namespace = "com.selfpro.realies.common"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies{
    implementation(project(":util:shared"))
    implementation(project(":data:model"))
    implementation(project(":util:common"))
    implementation(project(":data:network"))

    implementation(project(":util:icon"))
}
