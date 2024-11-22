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
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.ktlint.gradle.plugin)
    implementation(libs.vanniktech.maven.publish.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("lint") {
            id = "ktor.staticGeneration.build.logic.lint"
            implementationClass = "me.tbsten.ktor.staticGeneration.KtorStaticGenerationLintPlugin"
        }
        register("publish") {
            id = "ktor.staticGeneration.build.logic.publish"
            implementationClass =
                "me.tbsten.ktor.staticGeneration.KtorStaticGenerationPublishPlugin"
        }
    }
}
