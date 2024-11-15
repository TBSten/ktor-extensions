import me.tbsten.ktor.staticGeneration.KtorStaticGenerationTask

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    id("me.tbsten.ktor.static.generation") version "0.0.1"
}

group = "me.tbsten.ktor.static.generation.test"
version = "0.0.1-dev01"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    implementation("me.tbsten.ktor:ktor-static-generation-runtime:0.1.0-dev01")
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}

//// TODO move to gradle plugin
//tasks.create("staticGenerate", JavaExec::class.java) {
//    mainClass.set("me.tbsten.ktor.staticGeneration.test.sg.StaticGenerationKt")
//
//    this.classpath = sourceSets.main.get().runtimeClasspath
//
//    environment["ktor.staticGeneration.outputDir"] =
//        layout.buildDirectory
//            .dir("ktor-static-generate-output").get().asFile
//            .absolutePath
//}

val staticGenerate by tasks.getting(KtorStaticGenerationTask::class) {
    mainClass.set("me.tbsten.ktor.staticGeneration.test.sg.StaticGenerationKt")
    classpath = sourceSets.main.get().runtimeClasspath
}

/*
// TODO
staticGeneration { // this: JavaExec
    mainClass.set("me.tbsten.ktor.staticGeneration.test.sg.StaticGenerationKt")
    classpath.from(sourceSets.main) // TODO 適用されているpluginをみて自動でデフォルト値を設定する
    /* outputDir = layout.buildDirectory.dir("ktor-static-generate-output") */
}
 */
