package com.mc.toysimplemonkey.features.simple_consulting.compose.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mc.toysimplemonkey.features.simple_consulting.compose.presentation.section.SimConsultingDataSection
import com.mc.toysimplemonkey.features.simple_consulting.compose.presentation.section.SimpleConsultingPhoneSection
import com.mc.toysimplemonkey.features.simple_consulting.compose.presentation.section.SimpleConsultingResultSection
import com.mc.toysimplemonkey.features.simple_consulting.compose.presentation.section.SimpleConsultingTopSection
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation.ScScreenLoadingOverlayRoute
import com.mc.toysimplemonkey.ui.component.layout.CpOrientation
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpLayout
import com.mc.toysimplemonkey.ui.component.layout.responsive.OrientType
import com.mc.toysimplemonkey.ui.component.layout.scaffod.CpScaffold


@Composable
fun SimpleConsultingScreen() {
    CpScaffold(
        top = {
            SimpleConsultingTopSection()
        },
        body = {
            Body()
        },
        bottom = {
            SimpleConsultingResultSection()
        }
    )
    ScScreenLoadingOverlayRoute()
}





@Composable
private fun Body(
    modifier: Modifier = Modifier
){
    CpOrientation (
        modifier = modifier,
        landscape = {
            CpLayout(
                orientType = OrientType.ROW
            ) {
                item(3f) {
                    SimpleConsultingPhoneSection()
                }

                item(4f) {
                    SimConsultingDataSection()
                }
            }
        },
        portrait = {
            CpLayout() {
                item(4f) {
                    SimpleConsultingPhoneSection()
                }

                item(3f) {
                    SimConsultingDataSection()
                }
            }
        }
    )
}



@Preview(
    showBackground = true,
    name = "Landscape",
    device = "spec:width=411dp,height=891dp,orientation=landscape"
)
@Composable
private fun DefaultPreview(){
    SimpleConsultingScreen()
}

@Preview(
    showBackground = true,
    name = "Landscape",
    device = "spec:width=411dp,height=891dp,orientation=portrait"
)
@Composable
private fun DefaultCPreview(){
    SimpleConsultingScreen()
}