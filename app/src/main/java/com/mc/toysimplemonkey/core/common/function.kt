package com.mc.toysimplemonkey.core.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

fun Modifier.sideBorder(
    width: Dp,
    color: Color,
    start: Boolean = false,
    top: Boolean = false,
    end: Boolean = false,
    bottom: Boolean = false,
): Modifier = drawBehind {
    val stroke = width.toPx()
    val half = stroke / 2f   // 선이 영역 안쪽에 들어오도록 보정

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val left = if (isLtr) start else end
    val right = if (isLtr) end else start

    if (top) drawLine(color, Offset(0f, half), Offset(size.width, half), stroke)
    if (bottom) drawLine(color, Offset(0f, size.height - half), Offset(size.width, size.height - half), stroke)
    if (left) drawLine(color, Offset(half, 0f), Offset(half, size.height), stroke)
    if (right) drawLine(color, Offset(size.width - half, 0f), Offset(size.width - half, size.height), stroke)
}


@Composable
inline fun PreviewSwitch(
    preview: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    if (LocalInspectionMode.current) {
        preview()
    } else {
        content()
    }
}