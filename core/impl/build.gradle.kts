import com.psychojean.buildsrc.Base
import com.psychojean.buildsrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.psychojean.core.impl"
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    dependencies {
        api(project(":core:api"))

        implementation(Dependencies.AndroidX.Compose.ui)

        implementation(Dependencies.JavaX.inject)

        implementation(Dependencies.AndroidX.Compose.navigation)
        implementation(Dependencies.AndroidX.Compose.preview)
        implementation(Dependencies.AndroidX.Compose.debug)

        implementation(Dependencies.AndroidX.Compose.Material3.material)
        implementation(Dependencies.AndroidX.Compose.Material3.accompanist)
        implementation(Dependencies.AndroidX.Compose.Material3.adaptive)
        implementation(Dependencies.AndroidX.Compose.Material3.suite)
        implementation(Dependencies.AndroidX.Compose.Material3.window)

        implementation(Dependencies.AndroidX.Paging.common)
        implementation(Dependencies.AndroidX.Paging.compose)
        implementation(Dependencies.AndroidX.Paging.commonKtx)
        kapt(Dependencies.AndroidX.Paging.commonKtx)

        implementation(Dependencies.SquareUp.retrofit)

        implementation(Dependencies.Dagger.hilt)
        implementation(Dependencies.Dagger.hilt)
        kapt(Dependencies.Dagger.hiltCompiler)
    }
}