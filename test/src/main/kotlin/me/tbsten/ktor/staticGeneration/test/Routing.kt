package me.tbsten.ktor.staticGeneration.test

import io.ktor.http.ContentType
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing
import kotlinx.coroutines.flow.flowOf
import me.tbsten.ktor.staticGeneration.StaticGeneration
import me.tbsten.ktor.staticGeneration.staticGeneration

fun Application.configureRouting() {
    install(StaticGeneration)
    routing {
        staticGeneration("/") {
            call.respondText("<h1>Hello World!</h1>", ContentType.Text.Html)
        }
        staticGeneration(
            "/style.css",
            extension = "",
        ) {
            call.respondText(
                """
                html, body {
                    margin: 0;
                    padding: 0;
                    min-width: 100%;
                    min-height: 100%;
                }
                body {
                    background-color: red;
                    color: white;
                }
                """.trimIndent(),
                ContentType.Text.CSS,
            )
        }

        staticGeneration(
            "/blog/{blogId}/",
            staticPaths = { flowOf("/blog/1/", "/blog/2/", "/blog/3/") },
        ) {
            val blogId = call.parameters["blogId"]
            call.respondText(
                """
                <html lang="ja">
                <head>
                    <link rel="stylesheet" type="text/css" href="/style.css" /> 
                    <link rel="stylesheet" type="text/css" href="./style.css" /> 
                </head>
                <body>
                    <h1>Blog - $blogId</h1>
                </body>
                </html>
                """.trimIndent(),
                ContentType.Text.Html,
            )
        }
        staticGeneration(
            "/blog/{blogId}/style.css",
            staticPaths = { flowOf("/blog/1/style.css", "/blog/2/style.css", "/blog/3/style.css") },
            extension = "",
        ) {
            call.respondText(
                """
                h1 {
                    background-color: white;
                    color: red;
                }
                """.trimIndent(),
                ContentType.Text.CSS,
            )
        }
    }
}
