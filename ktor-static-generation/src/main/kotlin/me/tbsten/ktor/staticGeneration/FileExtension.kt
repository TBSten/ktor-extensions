package me.tbsten.ktor.staticGeneration

import io.ktor.http.ContentType

internal fun guessFileExtension(contentType: ContentType): String? {
    builtInFileExtensions.keys.forEach {
        if (contentType.match(it)) return builtInFileExtensions[it]
    }
    return null
}

private val builtInFileExtensions = mapOf(
    ContentType.Text.Html to ".html",
    ContentType.Text.CSS to ".css",
    ContentType.Text.JavaScript to ".js",
    ContentType.Text.Plain to ".txt",
    ContentType.Text.Xml to ".xml",
    ContentType.Application.Json to ".json",
    ContentType.Application.JavaScript to ".js",
    ContentType.Application.Xml to ".xml",
    ContentType.Image.GIF to ".gif",
    ContentType.Image.PNG to ".png",
    ContentType.Image.SVG to ".svg",
    ContentType.Image.JPEG to ".jpeg",
)