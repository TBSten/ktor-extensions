package me.tbsten.ktor.staticGeneration.routing

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import me.tbsten.ktor.staticGeneration.inferStaticPaths
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * refs: https://ktor.io/docs/server-routing.html
 */
class InferStaticPathsTest {
    private suspend fun inferStaticPathsList(path: String) = inferStaticPaths(path)?.invoke()?.toList()

    @Test
    fun testNoneParamsRoute() =
        runTest {
            val path = "/hello"
            val expected = listOf("/hello")
            val actual = inferStaticPathsList(path)
            assertEquals(expected, actual)
        }

    @Test
    fun testPathParameter1Route() =
        runTest {
            val path = "/user/{login}"
            val expected = null
            val actual = inferStaticPathsList(path)
            assertEquals(expected, actual)
        }

    @Test
    fun testPathParameter2Route() =
        runTest {
            val path = "/user/{login}/items"
            val expected = null
            val actual = inferStaticPathsList(path)
            assertEquals(expected, actual)
        }

    @Test
    fun testOptionalPathParameter1Route() =
        runTest {
            val path = "/user/{login?}"
            val expected = null
            val actual = inferStaticPathsList(path)
            assertEquals(expected, actual)
        }

    @Test
    fun testOptionalPathParameter2Route() =
        runTest {
            val path = "/user/{login?}/oauth"
            val expected = null
            val actual = inferStaticPathsList(path)
            assertEquals(expected, actual)
        }

    @Test
    fun testWildCardRoute() =
        runTest {
            val path = "/user/*"
            val expected = null
            val actual = inferStaticPathsList(path)
            assertEquals(expected, actual)
        }

    @Test
    fun testTailCardRoute() =
        runTest {
            val path = "/user/{login...}"
            val expected = null
            val actual = inferStaticPathsList(path)
            assertEquals(expected, actual)
        }
}
