package com.mc.toysimplemonkey.core.library.bloc

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
class StateHolder<T>(var value: T)

/**
 * 상태가 바뀌면 content를 다시 그린다.
 * buildWhen이 false를 반환하면 이전 상태로 유지한다.
 * 사용: BlocBuilder<CounterBloc, CounterState> { state -> ... }
 */
@Composable
inline fun <reified B : Bloc<*, S, *>, S : Any> BlocBuilder(
    bloc: B = blocOf<B>(),
    noinline buildWhen: ((previous: S, current: S) -> Boolean)? = null,
    content: @Composable (state: S) -> Unit,
) {
    val current by bloc.state.collectAsStateWithLifecycle()
    val holder = remember(bloc) { StateHolder(current) }
    if (buildWhen == null || buildWhen(holder.value, current)) holder.value = current
    content(holder.value)
}

/**
 * 일회성 Effect 수신 (스낵바, 네비게이션 등)
 * 사용: BlocListener<CounterBloc, CounterEffect> { effect -> ... }
 */
@Composable
inline fun <reified B : Bloc<*, *, F>, F : Any> BlocListener(
    bloc: B = blocOf<B>(),
    noinline listener: suspend (F) -> Unit,
) {
    val latest by rememberUpdatedState(listener)
    LaunchedEffect(bloc) { bloc.effects.collect { latest(it) } }
}

/**
 * 상태의 일부만 구독. 선택한 값이 바뀔 때만 리컴포지션.
 * 사용: BlocSelector<CounterBloc, CounterState, Int>(selector = { it.count }) { count -> ... }
 */
@Composable
inline fun <reified B : Bloc<*, S, *>, S : Any, R> BlocSelector(
    bloc: B = blocOf<B>(),
    noinline selector: (S) -> R,
    content: @Composable (R) -> Unit,
) {
    val selected by remember(bloc) {
        bloc.state.map(selector).distinctUntilChanged()
    }.collectAsStateWithLifecycle(initialValue = selector(bloc.state.value))
    content(selected)
}