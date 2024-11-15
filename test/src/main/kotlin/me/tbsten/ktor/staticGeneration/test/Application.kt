package me.tbsten.ktor.staticGeneration.test

import io.ktor.server.application.Application

fun main(args: Array<String>) {
    println(args)
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
}
