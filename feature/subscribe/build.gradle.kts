import com.selfpro.realies.build_logic.dsl.android

plugins {
    alias(libs.plugins.realies.android.feature)
}

android {
    namespace = "com.selfpro.realies.subscribe"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies{
    implementation(project(":util:icon"))
    implementation(project(":util:common"))
    implementation(project(":feature:common"))
    implementation(project(":data:network"))
    implementation(project(":util:shared"))
    implementation(project(":data:model"))
}
