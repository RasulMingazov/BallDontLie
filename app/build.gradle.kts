import com.psychojean.buildsrc.Base
import com.psychojean.buildsrc.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.psychojean.balldontlie"
    compileSdk = Base.compileSDK

    defaultConfig {
        applicationId = "com.psychojean.balldontlie"
        minSdk = Base.minSDK
        targetSdk = Base.targetSDK
        versionCode = Base.versionCode
        versionName = Base.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
}

dependencies {
    implementation(project(":core:impl"))
    api(project(":core:api"))

    implementation(project(":feature:player:impl"))
    api(project(":feature:player:api"))

    implementation(project(":feature:team:impl"))
    api(project(":feature:team:api"))

    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.lifecycleKtx)

    implementation(Dependencies.AndroidX.Compose.activity)
    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.preview)
    implementation(Dependencies.AndroidX.Compose.debug)
    implementation(Dependencies.AndroidX.Compose.navigation)

    implementation(Dependencies.AndroidX.Compose.Material3.material)
    implementation(Dependencies.AndroidX.Compose.Material3.window)
    implementation(Dependencies.AndroidX.Compose.Material3.suite)
    implementation(Dependencies.AndroidX.Compose.Material3.adaptive)
    implementation(Dependencies.AndroidX.Compose.Material3.accompanist)

    implementation(Dependencies.SquareUp.retrofit)
    implementation(Dependencies.SquareUp.okHttp)
    implementation(Dependencies.SquareUp.loggingInterceptor)
    implementation(Dependencies.SquareUp.converterGson)

    implementation(Dependencies.Moshi.moshi)
    implementation(Dependencies.Moshi.kotlin)
    implementation(Dependencies.Moshi.retrofit)
    kapt(Dependencies.Moshi.codegen)

    implementation(Dependencies.JavaX.inject)

    implementation(Dependencies.Dagger.hilt)
    kapt(Dependencies.Dagger.hiltCompiler)
    implementation(Dependencies.Dagger.Compose.compose)
}