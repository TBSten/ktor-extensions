import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.vanniktech.mavenPublish)
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

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

    signAllPublications()

    coordinates(
        groupId = group.toString(),
        artifactId = "ktor-static-generation",
        version = version.toString(),
    )

    val libraryGithubUrl = "https://github.com/tbsten/ktor-static-generation"

    pom {
        name.set("Ktor Static Generation Plugin")
        description.set("ktor plugin to output routes statically.")
        inceptionYear.set("2024")
        url.set(libraryGithubUrl)
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("tbsten")
                name.set("TBSten")
                url.set("https://github.com/tbsten/")
            }
        }
        scm {
            url.set(libraryGithubUrl)
            connection.set("scm:git:git://github.com/TBSten/ktor-static-generation.git")
            developerConnection.set("scm:git:ssh://git@github.com/TBSten/ktor-static-generation.git")
        }
    }
}
