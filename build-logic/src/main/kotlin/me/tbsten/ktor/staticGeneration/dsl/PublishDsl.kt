package me.tbsten.ktor.staticGeneration.dsl

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.mavenPublishing(actions: MavenPublishBaseExtension.() -> Unit) = extensions.configure(MavenPublishBaseExtension::class.java, actions)
