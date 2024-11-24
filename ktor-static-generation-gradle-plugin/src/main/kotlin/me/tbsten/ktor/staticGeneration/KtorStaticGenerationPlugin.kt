package me.tbsten.ktor.staticGeneration

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
open class KtorStaticGenerationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            tasks.register("staticGenerate", KtorStaticGenerationTask::class.java) {
                it.environment["ktor.staticGeneration.outputDir"] = it.outputDir
            }
        }
    }
}
