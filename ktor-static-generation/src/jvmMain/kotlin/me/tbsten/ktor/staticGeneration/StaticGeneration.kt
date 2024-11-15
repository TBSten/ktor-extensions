package me.tbsten.ktor.staticGeneration

import io.ktor.server.application.Application
import io.ktor.server.application.BaseApplicationPlugin
import io.ktor.util.AttributeKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.time.Duration

class StaticGeneration(config: StaticGenerationConfig) {
    private val registeredStaticPaths = mutableListOf<suspend () -> Flow<StaticGenerateRoute>>()
    internal val timeOutOfGenerateRoute: Duration = config.timeOutOfGenerateRoute
    internal val timeOutOfStaticPaths: Duration = config.timeOutOfStaticPaths

    internal fun registerStaticPaths(staticPaths: suspend () -> Flow<String>, extension: String?) {
        registeredStaticPaths.add {
            staticPaths()
                .map { StaticGenerateRoute(it, extension) }
        }
    }

    internal fun finalStaticPaths(): Flow<StaticGenerateRoute> =
        flow {
            println("finalStaticPaths: registeredStaticPaths.size:${registeredStaticPaths.size}")
            registeredStaticPaths.forEach { getStaticPaths ->
                getStaticPaths.invoke().collect { staticPath ->
                    println("finalStaticPaths:   staticPath:$staticPath")
                    emit(staticPath)
                }
            }
        }

    companion object Plugin :
        BaseApplicationPlugin<Application, StaticGenerationConfig, StaticGeneration> {
        override val key: AttributeKey<StaticGeneration> = AttributeKey("StaticGeneration")

        override fun install(
            pipeline: Application,
            configure: StaticGenerationConfig.() -> Unit,
        ): StaticGeneration {
            val config = StaticGenerationConfig().apply(configure)
            return StaticGeneration(config)
        }
    }
}
