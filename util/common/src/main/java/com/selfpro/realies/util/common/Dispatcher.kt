package com.selfpro.realies.util.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val realiesDispatcher: RealiesDispatcher)

enum class RealiesDispatcher {
    Default,
    IO,
    Main
}