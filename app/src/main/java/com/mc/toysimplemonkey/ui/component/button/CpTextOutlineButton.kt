package com.mc.toysimplemonkey.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mc.toysimplemonkey.R
import com.mc.toysimplemonkey.ui.component.CpIcon
import com.mc.toysimplemonkey.ui.component.CpText

@Composable
fun CpTextOutlineButton(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp = 4.dp,
    onClick: () -> Unit
){
    CpOutlineButton(
        modifier = modifier.fillMaxSize(),
        onClick = onClick,

        content = {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = padding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                CpText(
                    text = text
                )
                CpIcon(
                    id = R.drawable.ic_expand_more
                )
            }
        }
    )
}