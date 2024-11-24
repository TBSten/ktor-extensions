package me.tbsten.ktor.staticGeneration

import org.gradle.api.Project
import javax.inject.Inject

open class KtorExtensionsPublishExtension
    @Inject
    constructor(
        project: Project,
    ) {
        var groupId: String? = project.group.toString()
        lateinit var artifactId: String
        var version: String? = project.version.toString()

        var libraryName: String? = null
            get() = field ?: artifactId
        var libraryDescription: String = ""
    }
