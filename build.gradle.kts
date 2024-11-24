plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.vanniktechMavenPublish) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.ktor) apply false
    alias(libs.plugins.binaryCompatibilityValidator) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.ktorExtensionsLint) apply false
    alias(libs.plugins.ktorExtensionsPublish) apply false
}

allprojects {
    group = "me.tbsten.ktor"
    version =
        rootProject.libs.versions
            .ktorStaticGeneration
            .get()
}
