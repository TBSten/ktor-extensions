package me.tbsten.ktor.staticGeneration.test.sg

import kotlinx.coroutines.runBlocking
import me.tbsten.ktor.staticGeneration.generateStatic
import me.tbsten.ktor.staticGeneration.test.configureRouting

fun main() {
    runBlocking {
        generateStatic {
            configureRouting()
        }
    }
}
