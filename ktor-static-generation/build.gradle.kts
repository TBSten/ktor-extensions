import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "me.tbsten.ktor"
version = "0.1.0-dev01"

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.server.core)
                implementation(libs.ktor.server.resources)
                implementation(libs.ktor.server.test.host)
                implementation(libs.ktor.server.netty)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.ktor.server.test.host)
            }
        }
    }
}

android {
    namespace = "me.tbsten.ktor.static.generation"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "ktor-static-generation-runtime", version.toString())

    pom {
        name = "ktor static generation runtime"
        description = "A library to output (SG) the route of ktor's get method as a static file."
        inceptionYear = "2024"
        url = "https://github.com/tbsten/ktor-static-generation"
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
                url = "https://github.com/tbsten"
            }
        }
        scm {
            url = "https://github.com/tbsten/ktor-static-generation/"
            connection = "scm:git:git://github.com/tbsten/ktor-static-generation.git"
            developerConnection = "scm:git:ssh://git@github.com/tbsten/ktor-static-generation.git"
        }
    }
}
