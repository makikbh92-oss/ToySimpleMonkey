package com.mc.toysimplemonkey.features.simple_consulting.compose.presentation.section

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mc.toysimplemonkey.R
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation.ScTopSectionInitializedButton
import com.mc.toysimplemonkey.ui.component.CpIcon
import com.mc.toysimplemonkey.ui.component.CpText
import com.mc.toysimplemonkey.ui.component.FontType
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxFixed
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxUnFixed
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsivePadding
import com.mc.toysimplemonkey.ui.theme.AppTheme


@Composable
fun SimpleConsultingTopSection(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize().border(
            width = 1.5.dp,
            color = AppTheme.colors.line
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            LogoWithText()
            ScTopSectionInitializedButton()
        }
    }
}

@Composable
private fun LogoWithText(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        CpFitBoxFixed(
            modifier = Modifier.fillMaxHeight(),
            padding = ResponsivePadding.Default
        ){
            CpIcon(
                id = R.drawable.ic_simple_monkey
            )
        }
        CpFitBoxUnFixed(

            ) {
                CpText(
                    text = "심플몽키",
                    fontSize = 0.8f,
                    type = FontType.TITTLE,
                    textAlign = TextAlign.Center
                )
            }
    }
}





@Preview(showBackground = true, name = "TopSection")
@Composable
private fun DefaultPreview() {
    Box(
        Modifier
            .width(411.dp)
            .height(60.dp)   // 실제 상단 바 높이에 맞추기
    ) {
        SimpleConsultingTopSection()
    }
}