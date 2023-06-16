pluginManagement {
    repositories {
        mavenLocal()
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
