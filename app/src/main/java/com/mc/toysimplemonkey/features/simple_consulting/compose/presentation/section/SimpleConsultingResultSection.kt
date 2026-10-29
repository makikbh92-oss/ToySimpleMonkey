package com.mc.toysimplemonkey.features.simple_consulting.compose.presentation.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mc.toysimplemonkey.ui.component.CpText
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxUnFixed
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpLayout
import com.mc.toysimplemonkey.ui.component.layout.responsive.OrientType
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsivePadding
import com.mc.toysimplemonkey.ui.theme.AppTheme

@Composable
fun SimpleConsultingResultSection(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxSize().background(color = AppTheme.colors.secondaryBackground),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        CpLayout(
            orientType = OrientType.ROW
        ) {
            item {
                CpLayout() {

                    item(3f) {
                        CpFitBoxUnFixed(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            CpText(text = "월 납부금액", fontWeight = FontWeight.ExtraBold)
                        }
                    }
                    item(2f) {  }
                }
            }
            item{
                Result()
            }
        }


    }
}



@Composable
private fun Result(
    modifier: Modifier = Modifier
) {
    CpFitBoxUnFixed(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
    ) {
        CpLayout (
            Modifier.fillMaxSize(),
        ) {
            item(2f) {
                CpFitBoxUnFixed(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterEnd,
                    padding = ResponsivePadding.None
                ) {
                    CpText(
                        text = "92,500",
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold,
                        color = AppTheme.colors.accent
                    )
                }
            }
            item(1f) {
                CpFitBoxUnFixed(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterEnd,
                    padding = ResponsivePadding.None
                ) {
                    CpText(
                        text = "92,500",
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Medium,
                        color = AppTheme.colors.secondaryText
                    )
                }
            }
            item(1f) {
                CpFitBoxUnFixed(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterEnd,
                    padding = ResponsivePadding.None
                ) {
                    CpText(
                        text = "Vat",
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Medium,
                        color = AppTheme.colors.secondaryText
                    )
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
            .width(411.dp)
            .height(80.dp)   // 실제 상단 바 높이에 맞추기
    ) {
        SimpleConsultingResultSection()
    }
}