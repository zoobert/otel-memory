pluginManagement {
    repositories {
        mavenLocal()
        maven("https://repo.spring.io/snapshot")
        gradlePluginPortal()
    }
}
rootProject.name = "otel-memoryleak"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    "model",
    "service",
    "client",
)
