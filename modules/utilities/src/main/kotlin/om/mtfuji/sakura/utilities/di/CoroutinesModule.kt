package om.mtfuji.sakura.utilities.di

import om.mtfuji.sakura.utilities.DefaultDispatcherProvider
import om.mtfuji.sakura.utilities.DispatcherProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coroutineModule = module {
    singleOf(::DefaultDispatcherProvider).bind<DispatcherProvider>()
}