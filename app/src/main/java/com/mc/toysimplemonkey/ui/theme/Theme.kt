package com.mc.toysimplemonkey.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

@Composable
fun SimpleMonkeyTheme(
    colors: SimpleMonkeyColors = DefaultColorPalette,
    typography: SimpleMonkeyTypoGraphy = DefaultTypography,
    spacing: SimpleMonkeySpacing = SimpleMonkeySpacing(),
    content: @Composable () -> Unit
)   {

    CompositionLocalProvider(
        LocalSimpleMonkeyColors provides colors,
        LocalSimpleMonkeyTypography provides typography,
        LocalSimpleMonkeySpacing provides spacing,
    ){
        content()
    }
}

object AppTheme {
    val colors: SimpleMonkeyColors
        @Composable
        get() = LocalSimpleMonkeyColors.current

    val typography
        @Composable
        get() = LocalSimpleMonkeyTypography.current

    val spacing: SimpleMonkeySpacing
        @Composable
        get() = LocalSimpleMonkeySpacing.current
}