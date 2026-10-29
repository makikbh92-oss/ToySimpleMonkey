package com.mc.toysimplemonkey.ui.component.layout.reactive.utils

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable


class ReactiveScope internal constructor() {
    internal val items = mutableListOf<Item>()

    fun item(scale: Float = 1f, content: @Composable BoxScope.() -> Unit) {
        items += Item(scale, content)
    }

    @Immutable
    data class Item(
        val scale: Float,
        val content: @Composable BoxScope.() -> Unit,
    )
}