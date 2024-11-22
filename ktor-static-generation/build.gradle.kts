plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktorStaticGenerationLint)
    alias(libs.plugins.ktorStaticGenerationPublish)
}

kotlin {}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.resources)
    implementation(libs.ktor.server.test.host)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.kotlin.test.junit)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

ktorStaticGenerationPublish {
    artifactId = "ktor-static-generation"

    libraryName = "Ktor Static Generation Plugin"
    libraryDescription = "ktor plugin to output routes statically."
}
