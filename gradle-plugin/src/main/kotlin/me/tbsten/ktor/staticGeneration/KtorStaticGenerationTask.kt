package me.tbsten.ktor.staticGeneration

import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.JavaExec
import javax.inject.Inject

/**
 *
 * ```kt
 * staticGeneration { // this: JavaExec
 *     mainClass.set("me.tbsten.ktor.staticGeneration.test.sg.StaticGenerationKt")
 *     classpath.from(sourceSets.main) // TODO 適用されているpluginをみて自動でデフォルト値を設定する
 *     /* outputDir = layout.buildDirectory.dir("ktor-static-generate-output") */
 * }
 * ```
 */
open class KtorStaticGenerationTask
    @Inject
    constructor(project: Project) : JavaExec() {
        init {
            // TODO projectに適用されているpluginに応じてclasspathを設定
//        classpath.from(sourceSets.main)
        }

        @Input
        var outputDir: String =
            project
                .layout.buildDirectory
                .dir("ktor-static-generate-output")
                .get().asFile.absolutePath
    }
