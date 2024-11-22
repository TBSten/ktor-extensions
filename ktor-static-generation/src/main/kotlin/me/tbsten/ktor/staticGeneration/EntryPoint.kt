package me.tbsten.ktor.staticGeneration

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.application.Application
import io.ktor.server.application.plugin
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.runTestApplication
import io.ktor.utils.io.jvm.javaio.copyTo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.flow.toCollection
import me.tbsten.ktor.staticGeneration.util.lateInit
import java.io.File

@Suppress("unused")
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
suspend fun generateStatic(
    outputDir: File = defaultOutputDirOrThrow(),
    applicationModule: Application.() -> Unit,
) = coroutineScope {
    runTestApplication {
        var app by lateInit<Application>()
        install(StaticGeneration)
        application {
            applicationModule()
            app = this
        }
        startApplication()

        val sgPlugin = app.plugin(StaticGeneration)
        val staticPaths = sgPlugin.finalStaticPaths()

        val routes =
            mutableListOf<StaticGenerateRoute>()
                .let { sgPlugin.finalStaticPaths().toCollection(it) }
                .toList()

        staticPaths
            .timeout(sgPlugin.timeOutOfStaticPaths)
            .flatMapMerge { staticRoute ->
                println("SG: staticRoute:$staticRoute")
                flow {
                    emit(
                        generateStatic(
                            outputRootDir = outputDir,
                            route = staticRoute,
                        ),
                    )
                }.timeout(sgPlugin.timeOutOfGenerateRoute)
            }.collect()
    }
}

private suspend fun ApplicationTestBuilder.generateStatic(
    outputRootDir: File,
    route: StaticGenerateRoute,
) {
    val response = client.get(route.path)

    // validation
    if (response.status != HttpStatusCode.OK) {
        throw StaticGenerationErrors.InvalidStatusCodeError(
            path = route.path,
            statusCode = response.status,
        )
    }

    // infer extension
    val extension: String =
        run {
            val responseContentType = response.contentType()

            when {
                route.fileExtension != null ->
                    route.fileExtension

                responseContentType?.let { guessFileExtension(it) } != null ->
                    guessFileExtension(responseContentType)!!

                else ->
                    throw StaticGenerationErrors.InvalidFileExtensionError(
                        path = route.path,
                        contentType = responseContentType,
                    )
            }
        }

    // output to file
    val path =
        if (route.path.endsWith("/")) {
            "${route.path}index$extension"
        } else {
            "${route.path}$extension"
        }
    File(outputRootDir, path)
        .also {
            it.parentFile.mkdirs()
            println("SG: ${route.path} -> ${it.path}")
        }.outputStream()
        .use { response.bodyAsChannel().copyTo(it) }
}

@Suppress("ktlint:standard:property-naming")
internal const val OutputDirPropertyKey = "ktor.staticGeneration.outputDir"

private fun defaultOutputDir(): File? {
    System.getProperty(OutputDirPropertyKey, "").let { if (!it.isNullOrEmpty()) return File(it) }
    System.getenv(OutputDirPropertyKey)?.let { if (it.isNotEmpty()) return File(it) }
    return null
}

fun defaultOutputDirOrThrow(): File = defaultOutputDir() ?: throw StaticGenerationErrors.InvalidOutputDirPropertyError()
