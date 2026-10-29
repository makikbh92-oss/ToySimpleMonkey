package com.mc.toysimplemonkey.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class SimpleMonkeyColors(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val divider: Color,
    val line: Color,
    val surface: Color,
    val onSurface: Color,
    val secondaryText: Color,
    val active: Color,
    val onActive: Color,
    val accent: Color,
    val error: Color,
    val success: Color,
    val secondaryBackground: Color,
    )

val DefaultColorPalette = SimpleMonkeyColors(
    primary = Color(0xFF00E5FF),
    onPrimary = Color(0xFF000000),
    background = Color(0xFFfdfdfd),
    onBackground = Color(0xFF000000),
    divider = Color(0xFFc9cbce),
    line = Color(0xFFc7c9cb),
    surface = Color(0xFFf2f3f4),
    onSurface = Color(0xFF02020b),
    secondaryText = Color(0xFF606469),
    active = Color(0xFF5b5d64),
    onActive = Color(0xFFffffff),
    accent = Color(0xFFec266b),
    error = Color(0xFFEF4444),
    success = Color(0xFF10B981),
    secondaryBackground = Color(0xFFf0f0f2),

    )

val LocalSimpleMonkeyColors = staticCompositionLocalOf {
    DefaultColorPalette
}