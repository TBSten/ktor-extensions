plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

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
