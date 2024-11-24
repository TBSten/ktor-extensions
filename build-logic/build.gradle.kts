import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "me.tbsten.mirrorball.build.logic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(libs.kotlinGradlePlugin)
    implementation(libs.ktlintGradlePlugin)
    implementation(libs.vanniktechMavenPublishGradlePlugin)
}

gradlePlugin {
    plugins {
        register("lint") {
            id = "ktor.extensions.buildLogic.lint"
            implementationClass = "me.tbsten.ktor.staticGeneration.KtorExtensionsLintPlugin"
        }
        register("publish") {
            id = "ktor.extensions.buildLogic.publish"
            implementationClass =
                "me.tbsten.ktor.staticGeneration.KtorExtensionsPublishPlugin"
        }
    }
}
