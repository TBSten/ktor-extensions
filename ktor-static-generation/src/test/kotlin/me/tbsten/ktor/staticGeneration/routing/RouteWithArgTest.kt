package me.tbsten.ktor.staticGeneration.routing

import kotlinx.coroutines.flow.flowOf
import me.tbsten.ktor.staticGeneration.staticGeneration
import kotlin.test.Test

class RouteWithArgTest {
    @Test
    fun withArgsTest() = testStaticPaths(
        {
            staticGeneration(
                "/path/param/{blogId}",
                { flowOf("/path/param/1", "/path/param/2") },
            ) { TODO() }
            staticGeneration(
                "/wildcard/*",
                { flowOf("/wildcard/a") },
            ) { TODO() }
            staticGeneration(
                "/tailcard/{...}",
                { flowOf("/tailcard/a", "/tailcard/a/b/c") },
            ) { TODO() }
            staticGeneration(
                "/path/param/tailcard/{param...}",
                { flowOf("/path/param/tailcard/a", "/path/param/tailcard/a/b/c") },
            ) { TODO() }
        },
        listOf(
            "/path/param/1",
            "/path/param/2",
            "/wildcard/a",
            "/tailcard/a",
            "/tailcard/a/b/c",
            "/path/param/tailcard/a",
            "/path/param/tailcard/a/b/c",
        ),
    )

    // TODO 不正なRouteが指定された時の対処もテストする
}
