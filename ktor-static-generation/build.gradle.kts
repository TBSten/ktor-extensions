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
    testImplementation(libs.kotlinxCoroutinesTest)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

ktorStaticGenerationPublish {
    artifactId = "ktor-static-generation"

    libraryName = "Ktor Static Generation Plugin"
    libraryDescription = "ktor plugin to output routes statically."
}
signing {
    if (project.hasProperty("mavenCentralUsername") ||
        System.getenv("ORG_GRADLE_PROJECT_mavenCentralUsername") != null
    ) {
        useGpgCmd()
        // It is not perfect (fails at some dependency assertions), better handled as
        // `signAllPublications()` (as in vanniktech maven publish plugin) at build.gradle.kts.
        // sign(publishing.publications)
    }
}
