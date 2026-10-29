package com.mc.toysimplemonkey.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mc.toysimplemonkey.R

@Composable
fun CpRadioIcon(
    selected: Boolean,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
) {
    Image(
        painter = painterResource(
            if (selected) R.drawable.ic_radio_checked
            else R.drawable.ic_radio_unchecked
        ),
        contentDescription = if (selected) "선택됨" else "선택 안 됨",
        modifier = modifier.size(size),
    )
}