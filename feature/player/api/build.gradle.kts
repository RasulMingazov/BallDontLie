import com.psychojean.buildsrc.Dependencies

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core:api"))

    implementation(Dependencies.Moshi.retrofit)
    implementation(Dependencies.KotlinX.coroutine)
    implementation(Dependencies.AndroidX.Paging.common)
}