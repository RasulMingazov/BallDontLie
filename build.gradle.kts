buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(com.psychojean.buildsrc.Dependencies.Plugin.gradle)
        classpath(com.psychojean.buildsrc.Dependencies.Plugin.kotlin)
        classpath(com.psychojean.buildsrc.Dependencies.Plugin.navigationSafe)
        classpath(com.psychojean.buildsrc.Dependencies.Plugin.hilt)
        classpath(com.psychojean.buildsrc.Dependencies.Plugin.ktlint)
    }
}

apply(plugin = "org.jlleitschuh.gradle.ktlint")

tasks.register("clean").configure {
    delete("build")
}
