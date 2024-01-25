package com.psychojean.buildsrc

object Dependencies {

    object Plugin {
        const val gradle = "com.android.tools.build:gradle:${Versions.Plugin.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugin.kotlin}"
        const val navigationSafe =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Plugin.navigation}"
        const val secretGradle = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Versions.Plugin.gradle}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Plugin.hilt}"
        const val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.Plugin.ktlint}"

    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.android}"
        const val lifecycleKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"

        object Compose {
            const val activity =
                "androidx.activity:activity-compose:${Versions.AndroidX.Compose.activity}"
            const val ui = "androidx.compose.ui:ui:${Versions.AndroidX.Compose.compose}"
            const val preview =
                "androidx.compose.ui:ui-tooling-preview:${Versions.AndroidX.Compose.compose}"
            const val debug = "androidx.compose.ui:ui-tooling:${Versions.AndroidX.Compose.compose}"
            const val navigation =
                "androidx.navigation:navigation-compose:${Versions.AndroidX.Compose.navigation}"

            object Material3 {
                const val material =
                    "androidx.compose.material3:material3:${Versions.AndroidX.Compose.Material3.material}"
                const val window =
                    "androidx.compose.material3:material3-window-size-class:${Versions.AndroidX.Compose.Material3.material}"
                const val suite =
                    "androidx.compose.material3:material3-adaptive-navigation-suite:${Versions.AndroidX.Compose.Material3.suite}"
                const val adaptive = "androidx.compose.material3:material3-adaptive:${Versions.AndroidX.Compose.Material3.adaptive}"
                const val icons = "androidx.compose.material3:material3-ic:${Versions.AndroidX.Compose.Material3.adaptive}"
                const val accompanist = "com.google.accompanist:accompanist-navigation-material:${Versions.AndroidX.Compose.Material3.accompanist}"
            }
        }
    }

    object JavaX {
        const val inject = "javax.inject:javax.inject:${Versions.Java.javax}"
    }

    object KotlinX {
        const val coroutine =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KotlinX.coroutine}"
    }

    object Dagger {
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Dagger.hilt}"
        const val hilt = "com.google.dagger:hilt-android:${Versions.Dagger.hilt}"

        object Compose {
            const val compose =
                "androidx.hilt:hilt-navigation-compose:${Versions.Dagger.Compose.compose}"
        }
    }

}