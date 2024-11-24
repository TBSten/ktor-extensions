package me.tbsten.ktor.staticGeneration

import me.tbsten.ktor.staticGeneration.dsl.alias
import me.tbsten.ktor.staticGeneration.dsl.ktlint
import me.tbsten.ktor.staticGeneration.dsl.libs
import me.tbsten.ktor.staticGeneration.dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project

open class KtorStaticGenerationLintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                alias(libs.plugin("ktlint"))
            }
            ktlint {
            }
        }
    }
}
