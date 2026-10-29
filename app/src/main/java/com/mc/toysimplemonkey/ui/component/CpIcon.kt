package com.mc.toysimplemonkey.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun CpIcon(
    modifier: Modifier = Modifier,
    id: Int
){
    Icon(
        painter = painterResource(id),
        contentDescription = null,
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f),
        tint = Color.Unspecified
    )
}