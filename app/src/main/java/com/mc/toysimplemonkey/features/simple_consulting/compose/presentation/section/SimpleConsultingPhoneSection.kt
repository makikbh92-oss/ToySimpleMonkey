package com.mc.toysimplemonkey.features.simple_consulting.compose.presentation.section


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mc.toysimplemonkey.core.common.sideBorder

import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation.ScMobileSectionMakerButtonRoute
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation.ScMobileSectionPhoneButtonRoute
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation.ScMobileSectionTelecomButtonRoute

import com.mc.toysimplemonkey.ui.component.layout.responsive.CpLayout
import com.mc.toysimplemonkey.ui.component.layout.responsive.OrientType
import com.mc.toysimplemonkey.ui.theme.AppTheme


@Composable
fun SimpleConsultingPhoneSection(){
    CpLayout(
        modifier = Modifier.sideBorder(width = 1.dp, color = AppTheme.colors.line, bottom = true)
    ) {
        item(1.5f) {
            SelectButton(
                modifier = Modifier.fillMaxSize()
            )
        }

        item(9.5f) {
            ScMobileSectionPhoneButtonRoute()
        }
    }
}


@Composable
private fun SelectButton(
    modifier: Modifier= Modifier
){
    CpLayout(
        modifier = modifier,
        orientType = OrientType.ROW
    ) {
        item(1f) {
            ScMobileSectionTelecomButtonRoute()
        }

        item(1f) {
            ScMobileSectionMakerButtonRoute()
        }
    }
}



@Preview(showBackground = true, name = "TopSection")
@Composable
private fun DefaultPreview() {
    Box(
        Modifier
            .width(411.dp)
            .height(360.dp)   // 실제 상단 바 높이에 맞추기
    ) {
        SimpleConsultingPhoneSection()
    }
}