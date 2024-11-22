package me.tbsten.ktor.staticGeneration

import com.vanniktech.maven.publish.SonatypeHost
import me.tbsten.ktor.staticGeneration.dsl.alias
import me.tbsten.ktor.staticGeneration.dsl.libs
import me.tbsten.ktor.staticGeneration.dsl.mavenPublishing
import me.tbsten.ktor.staticGeneration.dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.gradle.api.publish.maven.MavenPomLicenseSpec

private object PublishConstants {
    const val AUTHOR_NAME = "TBSten"
    const val AUTHOR_URL = "https://tbsten.me"
    const val GITHUB_REPO = "ktor-static-generation"
    const val GITHUB_URL = "https://github.com/$AUTHOR_NAME/$GITHUB_REPO"
}

open class KtorStaticGenerationPublishPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                alias(libs.plugin("vanniktechMavenPublish"))
                alias(libs.plugin("binaryCompatibilityValidator"))
            }

            mavenPublishing {
                publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

                signAllPublications()
            }

            val publishExtension =
                extensions.create(
                    "ktorStaticGenerationPublish",
                    KtorStaticGenerationPublishExtension::class.java,
                )

            afterEvaluate {
                mavenPublishing {
                    coordinates(
                        groupId = publishExtension.groupId,
                        artifactId = publishExtension.artifactId,
                        version = publishExtension.version,
                    )

                    pom {
                        name.set(publishExtension.libraryName)
                        description.set(publishExtension.libraryDescription)
                        inceptionYear.set("2024")
                        url.set(PublishConstants.GITHUB_URL)

                        licenses {
                            apacheLicense20()
                        }

                        developers {
                            tbsten()
                        }

                        scm()
                    }
                }
            }
        }
    }
}

private fun MavenPomLicenseSpec.apacheLicense20() {
    license {
        name.set("The Apache License, Version 2.0")
        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
    }
}

private fun MavenPomDeveloperSpec.tbsten() {
    developer {
        id.set(PublishConstants.AUTHOR_NAME.lowercase())
        name.set(PublishConstants.AUTHOR_NAME)
        url.set(PublishConstants.AUTHOR_URL)
    }
}

private fun MavenPom.scm() {
    scm {
        url.set(PublishConstants.GITHUB_URL)
        connection.set("scm:git:git://github.com/${PublishConstants.AUTHOR_NAME}/${PublishConstants.GITHUB_REPO}")
        developerConnection.set("scm:git:ssh://git@github.com/${PublishConstants.AUTHOR_NAME}/${PublishConstants.GITHUB_REPO}.git")
    }
}
