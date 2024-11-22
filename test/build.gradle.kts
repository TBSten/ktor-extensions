import me.tbsten.ktor.staticGeneration.KtorStaticGenerationTask

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.ktorStaticGeneration)
}

group = "me.tbsten.ktor.static.generation.test"
version = "0.0.1-dev01"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

val staticGenerate by tasks.getting(KtorStaticGenerationTask::class) {
    mainClass.set("me.tbsten.ktor.staticGeneration.test.sg.StaticGenerationKt")
    classpath(sourceSets.main.get().runtimeClasspath)
}

dependencies {
    implementation(libs.ktorServerCore)
    implementation(libs.ktorServerNetty)
    implementation(libs.logbackClassic)
    implementation(libs.ktorServerConfigYaml)
    implementation(libs.ktorStaticGeneration)
}
