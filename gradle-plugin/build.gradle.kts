import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinJvm)
    `java-gradle-plugin`
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "me.tbsten.ktor"
version = "0.1.0-dev01"

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
            version = "0.0.1"
            implementationClass =
                "me.tbsten.ktor.staticGeneration.KtorStaticGenerationPlugin"
            displayName = "Ktor static generation plugin"
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}


mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "ktor-static-generation-gradle-plugin", version.toString())

    pom {
        name = "ktor static generation gradle plugin"
        description = "A library to output (SG) the route of ktor's get method as a static file."
        inceptionYear = "2024"
        url = "https://github.com/TBSten/ktor-static-generation"
        licenses {
            license {
                name = "MIT"
                url = "https://opensource.org/licenses/MIT"
                distribution = "https://opensource.org/licenses/MIT"
            }
        }
        developers {
            developer {
                id = "tbsten"
                name = "TBSten"
                url = "https://github.com/TBSten"
            }
        }
        scm {
            url = "https://github.com/TBSten/ktor-static-generation/"
            connection = "scm:git:git://github.com/TBSten/ktor-static-generation.git"
            developerConnection = "scm:git:ssh://git@github.com/TBSten/ktor-static-generation.git"
        }
    }
}
