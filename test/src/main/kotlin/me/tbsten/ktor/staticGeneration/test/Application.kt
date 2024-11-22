package me.tbsten.ktor.staticGeneration.test

import io.ktor.server.application.Application
import io.ktor.server.application.install
import me.tbsten.ktor.staticGeneration.StaticGeneration

fun main(args: Array<String>) {
    println(args)
    io.ktor.server.netty.EngineMain
        .main(args)
}

fun Application.module() {
    install(StaticGeneration)
    configureRouting()
}
