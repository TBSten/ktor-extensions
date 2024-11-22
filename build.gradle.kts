plugins {
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.vanniktech.mavenPublish) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.ktor) apply false
    alias(libs.plugins.binary.compatibility.validator) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.ktorStaticGenerationLint) apply false
    alias(libs.plugins.ktorStaticGenerationPublish) apply false
}

allprojects {
    group = "me.tbsten.ktor"
    version = rootProject.libs.versions.ktor.static.generation.get()
}
