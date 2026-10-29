package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation


import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier

import androidx.compose.ui.text.font.FontWeight

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mc.toysimplemonkey.core.common.PreviewSwitch


import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainAction
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainState
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainViewModel
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.phone.presentation.ScMobileDialogRoute
import com.mc.toysimplemonkey.ui.component.CpText
import com.mc.toysimplemonkey.ui.component.image.CpNetworkImage
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxFixed
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxUnFixed
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpLayout
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsivePadding


@Composable
fun ScMobileSectionPhoneButtonRoute() {
    PreviewSwitch(
        preview = {
            Mock()
        },
        content = {
            State()
        }
    )
}

@Composable
private fun State(
    viewModel: ScMainViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    Component(
        onClick = {
            viewModel.onAction(ScMainAction.ClickPhoneAction)
        },
        url = state.phone?.imageUrl.orEmpty(),
        name =  state.phone?.name?.takeIf { it.isNotBlank() } ?: "단말기 미선택",
    )
    val maker = state.maker
    if (state.showPhoneDialog && maker != null) {
        ScMobileDialogRoute(
            tkIdx = state.telecomType.index,
            mkIdx = maker.index,
            onDismiss = { viewModel.onAction(ScMainAction.DismissPhoneDialogAction) },
            onSelect = { phone ->

                viewModel.onAction(ScMainAction.SelectPhoneAction(phone = phone))
            }
        )
    }

}

@Composable
private fun Mock(
    state: ScMainState = ScMainState()
){
    Component(
        url = "",
        name = "프리뷰 모드",
    )
}

@Composable
private fun Component(
    modifier: Modifier = Modifier,
    url : String,
    name: String,
    onClick: () -> Unit = {}
){
    CpLayout(
        modifier = modifier
    ) {
        item(8.5f) {
            CpFitBoxFixed(
                modifier = Modifier.fillMaxSize(),
                padding = ResponsivePadding.Default
            ) {
                CpNetworkImage(
                    modifier = Modifier.fillMaxSize(),
                    url = url,
                    defaultText = "터치",
                    onClick = onClick
                )
            }
        }

        item(1.3f) {
            CpFitBoxUnFixed {
                CpText(
                    text = name,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

