package com.mc.toysimplemonkey.ui.component.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

private const val ORIGIN_WIDTH = 171f
private const val ORIGIN_HEIGHT = 64f
private const val ORIGIN_RATIO = ORIGIN_WIDTH / ORIGIN_HEIGHT

@Composable
fun CpTelImage(
    @DrawableRes resId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(resId),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .fillMaxHeight(0.85f)
            .aspectRatio(ORIGIN_RATIO)
    )
}