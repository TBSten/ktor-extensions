package me.tbsten.ktor.staticGeneration.routing

import io.ktor.server.routing.Routing
import me.tbsten.ktor.staticGeneration.staticGeneration
import org.junit.jupiter.api.Test

class BasicRoutingTest {
    private fun Routing.basicTestRouting() {
        staticGeneration("/") { TODO() }
        staticGeneration("/about") { TODO() }
    }

    private val expectedStaticPaths = listOf(
        "/",
        "/about",
    )

    @Test
    fun validBasicRoutingTest() = testStaticPaths(
        { basicTestRouting() },
        expectedStaticPaths,
    )
}
