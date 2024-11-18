plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

group = "me.tbsten.ktor"
version = "0.1.0-dev04"

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
