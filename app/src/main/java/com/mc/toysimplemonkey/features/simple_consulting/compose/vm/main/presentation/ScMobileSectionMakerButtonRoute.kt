package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mc.toysimplemonkey.core.common.PreviewSwitch
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO

import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainAction
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainState
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainViewModel
import com.mc.toysimplemonkey.ui.component.CpBottomSheet
import com.mc.toysimplemonkey.ui.component.button.CpTextOutlineButton
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxUnFixed
import com.mc.toysimplemonkey.ui.theme.AppTheme

@Composable
fun ScMobileSectionMakerButtonRoute() {
    PreviewSwitch(
        preview = {
            Mock()
        },
        content = {
            State()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun State(
    viewModel: ScMainViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    Component(
        onClick = {
            viewModel.onAction(ScMainAction.ClickMakerAction)
        },
        text = state.maker?.name.orEmpty()
    )
    if (state.showMakerSheet) {
        CpBottomSheet(
            items = state.makerList,
            itemKey = { it.index },
            onSelect = { viewModel.onAction(ScMainAction.SelectMakerAction(maker = it)) },
            onDismiss = { viewModel.onAction(ScMainAction.DismissMakerSheetAction) },
        ) { maker ->
            Text(text = maker.name, style = AppTheme.typography.button)
        }
    }

}

@Composable
private fun Mock(
    state: ScMainState = ScMainState()
){
    Component(
        text = state.maker?.name.orEmpty()
    )
}

@Composable
private fun Component(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
){
    CpFitBoxUnFixed(
        modifier = modifier
    ) {
        CpTextOutlineButton(
            text = text,
            onClick = onClick,
        )
    }
}
