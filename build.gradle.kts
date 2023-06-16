import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}

allprojects {
    group = "com.example"
    version = "0.0.1"

    repositories {
        mavenCentral()
        maven("https://repo.spring.io/snapshot")
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_19.toString()
        targetCompatibility = JavaVersion.VERSION_19.toString()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
            jvmTarget = JavaVersion.VERSION_19.toString()
        }
    }
}
