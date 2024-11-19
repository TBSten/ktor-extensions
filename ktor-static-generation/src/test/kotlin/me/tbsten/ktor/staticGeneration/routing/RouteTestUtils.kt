package me.tbsten.ktor.staticGeneration.routing

import io.ktor.server.application.install
import io.ktor.server.application.plugin
import io.ktor.server.routing.Routing
import io.ktor.server.routing.routing
import io.ktor.server.testing.testApplication
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.tbsten.ktor.staticGeneration.StaticGeneration
import kotlin.test.assertEquals

internal fun testStaticPaths(
    routing: Routing.() -> Unit,
    expectedStaticPaths: List<String>,
) {
    testApplication {
        var staticGeneration: StaticGeneration? = null
        application {
            install(StaticGeneration)
            routing {
                routing()
            }
            staticGeneration = plugin(StaticGeneration)
        }
        startApplication()
        assertEquals(
            expectedStaticPaths,
            staticGeneration?.finalStaticPaths()?.map { it.path }?.toList(),
        )
    }
}
