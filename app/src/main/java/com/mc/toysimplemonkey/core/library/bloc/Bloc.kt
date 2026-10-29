package com.mc.toysimplemonkey.core.library.bloc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * E: Event, S: State, F: Effect(일회성 이벤트: 스낵바, 네비게이션 등)
 * Effect가 필요 없으면 F = Nothing
 *
 * 이벤트는 add() 순서대로 하나씩 순차 처리된다.
 */
abstract class Bloc<E : Any, S : Any, F : Any>(initialState: S) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effects = Channel<F>(Channel.BUFFERED)
    val effects: Flow<F> = _effects.receiveAsFlow()

    private val events = Channel<E>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            for (event in events) {
                try {
                    handle(event)
                } catch (e: CancellationException) {
                    throw e
                } catch (t: Throwable) {
                    onError(t)
                }
            }
        }
    }

    /** 이벤트 전달 (Flutter bloc.add) */
    fun add(event: E) {
        events.trySend(event)
    }

    protected abstract suspend fun handle(event: E)

    protected open fun onError(error: Throwable) {
        error.printStackTrace()
    }

    protected fun emit(newState: S) {
        _state.value = newState
    }

    protected fun update(reducer: S.() -> S) {
        _state.update { it.reducer() }
    }

    protected suspend fun effect(effect: F) {
        _effects.send(effect)
    }

    protected val currentState: S get() = _state.value

    override fun onCleared() {
        events.close()
        _effects.close()
        super.onCleared()
    }
}