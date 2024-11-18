plugins {
    alias(libs.plugins.kotlinJvm)
    `java-gradle-plugin`
}

dependencies {
//    implementation(libs.kotlin.gradle.plugin)
//    implementation(libs.kotlinx.serialization)
//    implementation(libs.ktor.gradle.plugin)
//    implementation(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        create("ktor-static-generation") {
            id = "me.tbsten.ktor.static.generation"
            version = "0.1.0-dev04"
            implementationClass =
                "me.tbsten.ktor.staticGeneration.KtorStaticGenerationPlugin"
            displayName = "Ktor static generation plugin"
        }
    }
}
