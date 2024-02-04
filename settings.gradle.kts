pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BallDontLie"
include(":app")
include(":core:api")
include(":core:impl")
include(":feature:player:api")
include(":feature:player:impl")
include(":feature:team:api")
include(":feature:team:impl")
