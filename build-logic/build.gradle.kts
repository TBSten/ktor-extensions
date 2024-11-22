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
            id = "ktor.staticGeneration.buildLogic.lint"
            implementationClass = "me.tbsten.ktor.staticGeneration.KtorStaticGenerationLintPlugin"
        }
        register("publish") {
            id = "ktor.staticGeneration.buildLogic.publish"
            implementationClass =
                "me.tbsten.ktor.staticGeneration.KtorStaticGenerationPublishPlugin"
        }
    }
}
