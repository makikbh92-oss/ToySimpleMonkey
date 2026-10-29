package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mc.toysimplemonkey.core.common.PreviewSwitch
import com.mc.toysimplemonkey.core.common.VmStatus
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainState
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainViewModel
import com.mc.toysimplemonkey.ui.component.CpLoadingOverlay


@Composable
fun ScScreenLoadingOverlayRoute(){
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
    modifier: Modifier = Modifier,
    viewModel: ScMainViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    if (state.vmStatus == VmStatus.LOADING) {
        CpLoadingOverlay()
    }

}

@Composable
private fun Mock(
    modifier: Modifier = Modifier,
    state: ScMainState = ScMainState()
){
    if (state.vmStatus == VmStatus.LOADING) {
        CpLoadingOverlay()
    }

}