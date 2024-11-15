package me.tbsten.ktor.staticGeneration

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class StaticGenerationConfig {
    /**
     * Timeout duration for executing staticPaths. Default is 60 seconds.
     */
    var timeOutOfStaticPaths: Duration = 60.seconds

    /**
     * Timeout duration for each StaticGeneration Route. Default is 30 seconds.
     */
    var timeOutOfGenerateRoute: Duration = 30.seconds
}
