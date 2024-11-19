package me.tbsten.ktor.staticGeneration.util

import kotlin.reflect.KProperty

internal fun <T> lateInit() = LateInit<T>()

internal class LateInit<T> internal constructor() {
    private var state: State<T> = State.NotInit()

    var value: T
        get() = this.state.value
        set(newValue) {
            this.state = State.Ready(newValue)
        }

    val isInitialized: Boolean
        get() = this.state is State.Ready

    operator fun getValue(t: T?, property: KProperty<*>): T {
        return this.value
    }

    operator fun setValue(t: T?, property: KProperty<*>, newValue: T) {
        this.value = newValue
    }

    sealed interface State<T> {
        val value: T

        class NotInit<T> : State<T> {
            override val value: T
                get() = throw IllegalStateException("Not initialized")
        }

        class Ready<T>(override val value: T) : State<T>
    }
}
