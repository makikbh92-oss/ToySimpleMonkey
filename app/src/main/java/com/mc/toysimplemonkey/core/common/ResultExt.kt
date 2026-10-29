package com.mc.toysimplemonkey.core.common

import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <T> safeCall(crossinline block: suspend () -> T): Result<T> =
    try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }