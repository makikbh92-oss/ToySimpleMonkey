package com.mc.toysimplemonkey.ui.component

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mc.toysimplemonkey.ui.theme.AppTheme

enum class FontType {
    BUTTON, TITTLE, TEXT
}

@Composable
fun CpText(
    modifier: Modifier = Modifier,
    text: String,
    type: FontType = FontType.TEXT,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = AppTheme.colors.onBackground,
    textAlign: TextAlign? = null,
    fontRatio: Float = 0.7f,
    fontSize: Float = 1f
){
    BoxWithConstraints(
        modifier.fillMaxHeight(fontSize),
        contentAlignment = Alignment.Center
    ) {
        val fontSize = (maxHeight.value*fontRatio).sp
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = when(type){
                FontType.BUTTON -> FontWeight.Medium
                FontType.TITTLE -> FontWeight.Bold
                FontType.TEXT -> FontWeight.Normal
            },
            color = color,
            textAlign= textAlign
        )
    }
}