package me.tbsten.ktor.staticGeneration

import io.ktor.server.application.MissingApplicationPluginException
import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing
import io.ktor.server.routing.RoutingContext
import io.ktor.server.routing.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun Routing.staticGeneration(
    path: String,
    staticPaths: suspend () -> Flow<String> =
        inferStaticPaths(path)
            ?: throw StaticGenerationErrors.CanNotInferStaticPathsError(path),
    extension: String? = null,
    body: suspend RoutingContext.() -> Unit,
) = get(
    path = path,
    body = body,
).registerStaticPaths(staticPaths, extension).also { println("Route.registerStaticPaths: $path") }

// TODO
fun inferStaticPaths(path: String): (suspend () -> Flow<String>)? {
    // TODO
    val isNoneParam = true
    if (isNoneParam) return { flowOf(path) }
    return null
}

fun Routing.staticGeneration(
    path: Regex,
    staticPaths: suspend () -> Flow<String>,
    extension: String? = null,
    body: suspend RoutingContext.() -> Unit,
) = get(
    path = path,
    body = body,
).registerStaticPaths(staticPaths, extension).also { println("Route.registerStaticPaths: $path") }

private fun Route.registerStaticPaths(
    staticPaths: suspend () -> Flow<String>,
    extension: String?,
) {
    try {
        val sgPlugin = plugin(StaticGeneration)
        sgPlugin.registerStaticPaths(staticPaths, extension)
    } catch (e: MissingApplicationPluginException) {
        throw StaticGenerationErrors.NotConfiguredStaticGenerationPlugin(e)
    }
}
