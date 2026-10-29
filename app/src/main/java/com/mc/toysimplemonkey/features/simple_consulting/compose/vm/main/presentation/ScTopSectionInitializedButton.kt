package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainAction
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainViewModel
import com.mc.toysimplemonkey.ui.component.CpText
import com.mc.toysimplemonkey.ui.component.FontType
import com.mc.toysimplemonkey.ui.component.button.CpOutlineButton
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxFixed
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxUnFixed
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsivePadding


@Composable
fun ScTopSectionInitializedButton(
    modifier: Modifier = Modifier,
    viewModel: ScMainViewModel = hiltViewModel()
) {
    CpFitBoxUnFixed (
        modifier = modifier,
        padding = ResponsivePadding.Default
    ) {
        CpOutlineButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = {
                viewModel.onAction(ScMainAction.SelectTelecomAction(isInitial = true))
            },
        ){
            CpText(
                text = "초기화",
                type = FontType.BUTTON,
                fontSize = 0.9f
            )
        }
    }
}