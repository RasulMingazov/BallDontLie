import com.psychojean.buildsrc.Base
import com.psychojean.buildsrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.psychojean.feature.team.impl"
    compileSdk = Base.compileSDK

    defaultConfig {
        minSdk = Base.minSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core:impl"))

    api(project(":feature:team:api"))

    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleKtx)
    implementation(Dependencies.AndroidX.Compose.activity)
    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.preview)
    implementation(Dependencies.AndroidX.Compose.debug)

    implementation(Dependencies.SquareUp.retrofit)

    implementation(Dependencies.AndroidX.Compose.navigation)

    implementation(Dependencies.Moshi.retrofit)

    implementation(Dependencies.AndroidX.Compose.Material3.material)
    implementation(Dependencies.AndroidX.Compose.Material3.window)
    implementation(Dependencies.AndroidX.Compose.Material3.suite)
    implementation(Dependencies.AndroidX.Compose.Material3.adaptive)
    implementation(Dependencies.AndroidX.Compose.Material3.accompanist)
    implementation(Dependencies.AndroidX.Compose.material)

    implementation(Dependencies.JavaX.inject)

    implementation(Dependencies.Dagger.hilt)
    kapt(Dependencies.Dagger.hiltCompiler)
    implementation(Dependencies.Dagger.Compose.compose)
}