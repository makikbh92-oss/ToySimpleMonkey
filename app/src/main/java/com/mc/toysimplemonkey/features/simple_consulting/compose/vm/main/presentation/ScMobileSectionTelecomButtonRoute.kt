package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.mc.toysimplemonkey.R
import com.mc.toysimplemonkey.core.common.PreviewSwitch
import com.mc.toysimplemonkey.core.common.toIconRes
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.TelecomType

import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainAction
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainState
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main.ScMainViewModel
import com.mc.toysimplemonkey.ui.component.CpBottomSheet
import com.mc.toysimplemonkey.ui.component.CpIcon
import com.mc.toysimplemonkey.ui.component.button.CpOutlineButton
import com.mc.toysimplemonkey.ui.component.image.CpTelImage
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpFitBoxUnFixed

import com.mc.toysimplemonkey.ui.theme.AppTheme

@Composable
fun ScMobileSectionTelecomButtonRoute() {
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
            viewModel.onAction(ScMainAction.ClickTelecomAction)
        },
        resId = state.telecomType.toIconRes()
    )
    if (state.showTelSheet) {
        CpBottomSheet(
            items = TelecomType.entries,
            itemKey = { it.name },
            onSelect = { viewModel.onAction(ScMainAction.SelectTelecomAction(it)) },
            onDismiss = { viewModel.onAction(ScMainAction.DismissTelecomSheetAction) },
        ) { tel ->
            Text(text = tel.name, style = AppTheme.typography.button)
        }



    }

}

@Composable
private fun Mock(
    state: ScMainState = ScMainState()
){
    Component(
        resId = TelecomType.SK.toIconRes(),
    )
}

@Composable
private fun Component(
    modifier: Modifier = Modifier,
    @DrawableRes resId: Int,
    onClick: () -> Unit = {}
){
    CpFitBoxUnFixed{

        CpOutlineButton(
            modifier = modifier.fillMaxSize(),
            onClick = onClick,
            content = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    CpTelImage(
                        resId = resId,
                        modifier = Modifier
                    )
                    CpIcon(
                        id = R.drawable.ic_expand_more
                    )
                }
            }
        )
    }
}
