package me.tbsten.ktor.staticGeneration

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode

object StaticGenerationErrors {
    class NotConfiguredStaticGenerationPlugin : StaticGenerationException(
        "Not configured StaticGeneration plugin.",
        "Please configure `install(StaticGeneration)`.",
    )

    class CanNotInferStaticPathsError(path: String) : StaticGenerationException(
        "Can not infer static paths from path:$path.",
        "Please specify staticPaths explicitly.",
    )

    class InvalidStaticPathsError(routePath: String, path: String) :
        StaticGenerationException(
            "Invalid static path specified by route in $routePath: path=`$path`.",
            "Please make sure to pass a matching path to routePath",
        )

    class InvalidOutputDirPropertyError :
        StaticGenerationException(
            "Invalid property `$OutputDirPropertyKey`.",
        )

    class InvalidStatusCodeError(path: String, statusCode: HttpStatusCode) :
        StaticGenerationException(
            "Invalid response: path=`$path` status_code=$statusCode.",
            "Please make sure that `$path` responds with StatusCode: 200 when accessing $path.",
        )

    class InvalidFileExtensionError(path: String, contentType: ContentType?) :
        StaticGenerationException(
            "Could not guess the file extension: path=`$path` Content-Type=$contentType.",
            "Please make sure you have set the Content-Type correctly. Or consider using staticGeneration(extension).",
        )
}

abstract class StaticGenerationException internal constructor(
    message: String,
    recovery: String?,
    cause: Throwable?,
) : Exception(
    message + if (recovery == null) "" else ".\n$recovery",
    cause,
) {
    constructor(message: String) : this(message, null, null)
    constructor(message: String, recovery: String) : this(message, recovery, null)
    constructor(message: String, cause: Throwable?) : this(message, null, cause)
}
