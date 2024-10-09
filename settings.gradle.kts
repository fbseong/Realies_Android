enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:testClasses"))
rootProject.name = "Realies_Android"
include(
    ":app",
    ":data:model",
    ":data:network",
    ":feature:add",
    ":feature:challenge",
    ":feature:common",
    ":feature:my",
    ":feature:realies",
    ":feature:search",
    ":feature:subscribe",
    ":util:common",
    ":util:icon",
    ":util:shared"
)
include(":data:domain")
include(":feature:viewer")
include(":feature:sign")
include(":data:datastore")
include(":data:gemini")
