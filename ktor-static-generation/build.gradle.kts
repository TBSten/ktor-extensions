plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktorStaticGenerationLint)
    alias(libs.plugins.ktorStaticGenerationPublish)
}

kotlin {}

dependencies {
    implementation(libs.ktorServerCore)
    implementation(libs.ktorServerResources)
    implementation(libs.ktorServerTestHost)
    implementation(libs.ktorServerNetty)
    testImplementation(libs.kotlinTestJunit)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

ktorStaticGenerationPublish {
    artifactId = "ktor-static-generation"

    libraryName = "Ktor Static Generation Plugin"
    libraryDescription = "ktor plugin to output routes statically."
}
