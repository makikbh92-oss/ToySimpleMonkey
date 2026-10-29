package com.mc.toysimplemonkey.core.library.bloc

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.reflect.KClass

/** 타입(KClass) -> 인스턴스 저장소. 부모 레지스트리 위에 계속 합쳐진다. */
@Immutable
class BlocRegistry(private val map: Map<KClass<*>, Any> = emptyMap()) {
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(kClass: KClass<T>): T? = map[kClass] as? T
    fun with(kClass: KClass<*>, value: Any) = BlocRegistry(map + (kClass to value))
}

val LocalBlocRegistry = compositionLocalOf { BlocRegistry() }

// ───────────── Provider / read ─────────────

/** 범용 Provider. 하위 트리에서 readOf<T>()로 꺼낸다. */
@Composable
inline fun <reified T : Any> Provider(
    value: T,
    noinline content: @Composable () -> Unit,
) {
    val parent = LocalBlocRegistry.current
    val registry = remember(parent, value) { parent.with(T::class, value) }
    CompositionLocalProvider(LocalBlocRegistry provides registry, content = content)
}

/** context.read<T>() */
@Composable
inline fun <reified T : Any> readOf(): T =
    LocalBlocRegistry.current.get(T::class)
        ?: error("${T::class.simpleName} 이(가) Provider 위에 없습니다")

// ───────────── BlocProvider ─────────────

/** Bloc은 ViewModel로 생성되어 화면 회전에도 유지된다. Hilt면 bloc = hiltViewModel() 전달 */
@Composable
inline fun <reified B : Bloc<*, *, *>> BlocProvider(
    bloc: B = viewModel<B>(),
    noinline content: @Composable () -> Unit,
) = Provider(bloc, content)

@Composable
inline fun <reified B : Bloc<*, *, *>> blocOf(): B = readOf<B>()

// ───────────── MultiProvider ─────────────

data class ProviderEntry(val kClass: KClass<*>, val value: Any)

inline fun <reified T : Any> provide(value: T) = ProviderEntry(T::class, value)

@Composable
inline fun <reified B : Bloc<*, *, *>> provideBloc(
    bloc: B = viewModel<B>(),
) = ProviderEntry(B::class, bloc)

@Composable
fun MultiProvider(
    vararg providers: ProviderEntry,
    content: @Composable () -> Unit,
) {
    val parent = LocalBlocRegistry.current
    val list = providers.toList()
    val registry = remember(parent, list) {
        list.fold(parent) { acc, e -> acc.with(e.kClass, e.value) }
    }
    CompositionLocalProvider(LocalBlocRegistry provides registry, content = content)
}