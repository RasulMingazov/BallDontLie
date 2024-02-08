import com.psychojean.buildsrc.Dependencies

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(project(":core:api"))

    implementation(Dependencies.Moshi.retrofit)
    implementation(Dependencies.KotlinX.coroutine)
}