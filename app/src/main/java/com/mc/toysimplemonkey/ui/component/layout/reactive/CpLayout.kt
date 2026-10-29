package com.mc.toysimplemonkey.ui.component.layout.responsive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mc.toysimplemonkey.ui.component.layout.reactive.utils.ReactiveScope

enum class  OrientType{
    ROW, COLUMN
}



@Composable
fun CpLayout(
    modifier: Modifier = Modifier,
    orientType: OrientType = OrientType.COLUMN,
    contentAlignment: Alignment = Alignment.Center,
    content: ReactiveScope.() -> Unit,
) {
    val items = ReactiveScope().apply(content).items
    val fill = modifier.fillMaxSize()

    when (orientType) {
        OrientType.COLUMN -> Column(fill,

        ) {
            items.forEach { item ->
                Box(
                    Modifier.weight(item.scale.coerceAtLeast(0.01f)).fillMaxWidth(),
                    contentAlignment = contentAlignment,
                    content = item.content,
                )
            }
        }
        OrientType.ROW -> Row(fill,

            ) {
            items.forEach { item ->
                Box(
                    Modifier.weight(item.scale.coerceAtLeast(0.01f)).fillMaxHeight(),
                    contentAlignment = contentAlignment,
                    content = item.content
                )
            }
        }
    }
}
