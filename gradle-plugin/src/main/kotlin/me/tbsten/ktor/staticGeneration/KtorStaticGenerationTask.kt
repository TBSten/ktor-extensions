package me.tbsten.ktor.staticGeneration

import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.JavaExec
import javax.inject.Inject

open class KtorStaticGenerationTask
    @Inject
    constructor(
        project: Project,
    ) : JavaExec() {
        init {
            // TODO projectに適用されているpluginに応じてclasspathを設定
//        classpath(sourceSets.main.get().runtimeClasspath)
        }

        @Input
        var outputDir: String =
            project
                .layout.buildDirectory
                .dir("ktor-static-generate-output")
                .get()
                .asFile.absolutePath
    }
