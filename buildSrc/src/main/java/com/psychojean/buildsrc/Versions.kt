package com.psychojean.buildsrc

object Versions {

    object Plugin {
        const val application = "8.2.1"
        const val kotlin = "1.9.0"
        const val navigation = "2.7.6"
        const val hilt = "2.50"
        const val jvm = "1.9.0"
        const val gradle = "8.2.0"
        const val ktlint = "10.3.0"
    }

    object Core {
        const val kotlin = "1.6.21"
        const val coroutine = "1.7.3"
    }

    object AndroidX {
        const val android = "1.12.0"
        const val lifecycle = "2.7.0"

        object Compose {
            const val compose = "1.5.4"
            const val activity = "1.8.2"
            const val navigation = "2.7.6"

            object Material3 {
                const val material = "1.1.2"
                const val adaptive = "1.0.0-alpha04"
                const val suite = "1.0.0-alpha02"
                const val accompanist = "0.32.0"
            }
        }
    }

    object Java {
        const val javax = "1"
    }

    object KotlinX {
        const val coroutine = "1.7.3"
    }

    object Dagger {
        const val hilt = "2.50"

        object Compose {
            const val compose = "1.1.0"
        }
    }

}