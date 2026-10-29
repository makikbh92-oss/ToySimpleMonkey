package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mc.toysimplemonkey.core.common.sideBorder
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.plan.presentation.ScPlanDialogRoute
import com.mc.toysimplemonkey.ui.component.CpText
import com.mc.toysimplemonkey.ui.component.button.CpTextOutlineButton
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxUnFixed
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpLayout
import com.mc.toysimplemonkey.ui.component.layout.responsive.OrientType
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsivePadding
import com.mc.toysimplemonkey.ui.theme.AppTheme


@Composable
fun SimConsultingDataSection(){
    CpLayout(

    ) {
        item(2f) {
            DataRow(
                title = "요금제",
                useSTitle = true
            ){
                ScPlanDialogRoute()
            }
        }
        item() {
            DataRow(
                title = "지원금 유형"
            ){

            }
        }
        item() {
            DataRow(
                title = "출고가"
            ){

            }
        }
        item() {
            DataRow(
                title = "공시지원금"
            ){

            }
        }
        item() {
            DataRow(
                title = "기타할인"
            ){

            }
        }
        item() {
            DataRow(
                title = "할부원금"
            ){

            }
        }
        item() {
            DataRow(
                title = "할부개월"
            ){
                CpTextOutlineButton(
                    text = "24개월",
                    onClick = {}
                )
            }
        }
        item() {
            DataRow(
                title = "기본료"
            ){

            }
        }
        item() {
            DataRow(
                title = "선택약정 할인"
            ){

            }
        }
    }
}




@Composable
private fun DataRow(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String? = null,
    useSTitle: Boolean = false,
    content: @Composable (() -> Unit)? = null
) {
    Box(
        modifier.fillMaxSize()
    ) {
        CpLayout(
            Modifier.fillMaxSize(),
            orientType = OrientType.ROW
        ) {
            item(2f) {
                CpFitBoxUnFixed(
                   modifier = Modifier
                       .fillMaxSize()
                       .sideBorder(
                           width = 1.dp,
                           color = AppTheme.colors.line,
                           start = true,
                           end = true,
                           bottom = true
                       )

               ) {
                   if(useSTitle){
                       CpLayout(
                           contentAlignment = Alignment.CenterStart
                       ) {
                           item{
                               CpFitBoxUnFixed(
                                   padding = ResponsivePadding.None,
                                   modifier = Modifier.fillMaxSize()
                               ) {
                                   CpText(text = title, fontSize = 0.8f,)
                               }
                           }
                           item{ }
                       }
                   }
                   else {
                       CpText(text = title, fontSize = 0.8f,)
                   }

               }
            }
            item(6.5f){
                CpFitBoxUnFixed(
                    modifier = Modifier.fillMaxSize()
                        .sideBorder(
                            width = 1.dp,
                            color = AppTheme.colors.line,
                            end = true,
                            bottom = true
                        ),
                    padding = ResponsivePadding.VerticalHalf
                ) {
                    if(subtitle != null){
                        CpText(text = subtitle)
                    }
                    if(content != null){
                        content()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "TopSection")
@Composable
private fun DefaultPreview() {
    Box(
        Modifier
            .width(511.dp)
            .height(360.dp)   // 실제 상단 바 높이에 맞추기
    ) {
        SimConsultingDataSection()
    }
}