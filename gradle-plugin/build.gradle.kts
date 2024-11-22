plugins {
    alias(libs.plugins.kotlinJvm)
    `java-gradle-plugin`
    alias(libs.plugins.ktorStaticGenerationLint)
    alias(libs.plugins.ktorStaticGenerationPublish)
}

sourceSets {}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin.api)
//    implementation(libs.kotlinx.serialization)
//    implementation(libs.ktor.gradle.plugin)
//    implementation(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        create("ktor-static-generation") {
            id = "me.tbsten.ktor.static.generation"
            implementationClass =
                "me.tbsten.ktor.staticGeneration.KtorStaticGenerationPlugin"
            displayName = "Ktor static generation plugin"
        }
    }
}

ktorStaticGenerationPublish {
    artifactId = "ktor-static-generation-gradle-plugin"

    libraryName = "Ktor Static Generation Gradle Plugin"
    libraryDescription = "ktor plugin to output routes statically."
}
