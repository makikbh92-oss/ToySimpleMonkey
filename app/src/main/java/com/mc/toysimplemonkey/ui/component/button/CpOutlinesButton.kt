package com.mc.toysimplemonkey.ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsiveDp
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsivePadding
import com.mc.toysimplemonkey.ui.theme.AppTheme

@Composable
fun rememberResponsiveBase(): Dp {
    val config = LocalConfiguration.current
    return maxOf(config.screenWidthDp, config.screenHeightDp).dp
}

@Composable
fun CpOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.line,
    borderWidth: ResponsiveDp = ResponsiveDp.scaled(1.5.dp),
    cornerRadius: ResponsiveDp = ResponsiveDp.scaled(8.dp),
    padding: ResponsivePadding = ResponsivePadding.Button,
    content: @Composable () -> Unit
) {
    val base = rememberResponsiveBase()
    val shape = RoundedCornerShape(cornerRadius.resolve(base))

    Box(
        modifier = modifier
            .border(BorderStroke(width = borderWidth.resolve(base), color), shape)
            .clip(shape)
            .clickable(onClick = onClick)
            .padding(padding.resolve(base)),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
