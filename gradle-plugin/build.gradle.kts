import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinJvm)
    `java-gradle-plugin`
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.binary.compatibility.validator)
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

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

    signAllPublications()

    coordinates(
        groupId = group.toString(),
        artifactId = "ktor-static-generation-gradle-plugin",
        version = version.toString(),
    )

    val libraryGithubUrl = "https://github.com/tbsten/ktor-static-generation"

    pom {
        name.set("Ktor Static Generation Gradle Plugin")
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
