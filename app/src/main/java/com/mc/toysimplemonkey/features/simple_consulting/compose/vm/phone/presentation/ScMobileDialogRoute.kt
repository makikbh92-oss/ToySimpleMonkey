package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.phone.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.mc.toysimplemonkey.core.common.PreviewSwitch
import com.mc.toysimplemonkey.core.common.VmStatus
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.phone.ScPhoneAction
import com.mc.toysimplemonkey.features.simple_consulting.compose.vm.phone.ScPhoneViewModel
import com.mc.toysimplemonkey.ui.component.CpDialog
import com.mc.toysimplemonkey.ui.component.CpRadioIcon
import com.mc.toysimplemonkey.ui.component.CpText
import com.mc.toysimplemonkey.ui.component.button.CpOutlineButton
import com.mc.toysimplemonkey.ui.theme.AppTheme
import java.text.NumberFormat
import java.util.Locale

// ───────────── Route ─────────────
@Composable
fun ScMobileDialogRoute(
    tkIdx: Int,
    mkIdx: Int,
    onDismiss: () -> Unit,
    onSelect: (PhoneVO) -> Unit,
) {
    PreviewSwitch(
        preview = {
            Mock(onDismiss = onDismiss)
        },
        content = {
            State(
                tkIdx = tkIdx,
                mkIdx = mkIdx,
                onDismiss = onDismiss,
                onSelect = onSelect,
            )
        }
    )
}

// ───────────── State (ViewModel 연결) ─────────────
@Composable
private fun State(
    tkIdx: Int,
    mkIdx: Int,
    onDismiss: () -> Unit,
    onSelect: (PhoneVO) -> Unit,
) {
    val owner = rememberDialogViewModelStoreOwner()

    CompositionLocalProvider(LocalViewModelStoreOwner provides owner) {
        val vm = hiltViewModel<ScPhoneViewModel, ScPhoneViewModel.Factory>(
            creationCallback = { factory -> factory.create(tkIdx, mkIdx) }
        )
        val state by vm.state.collectAsStateWithLifecycle()

        Component(
            vmStatus = state.vmStatus,
            phoneList = state.phoneList,
            selectedPhone = state.selectedPhone,
            onDismiss = onDismiss,
            onPhoneClick = { phone ->
                vm.onAction(ScPhoneAction.SelectMobileAction(phone = phone))
            },
            onConfirm = {
                vm.onAction(ScPhoneAction.ConfirmAction { phone -> onSelect(phone) })
            },
        )
    }
}

// ───────────── Mock (프리뷰용, Hilt 없음) ─────────────
@Composable
private fun Mock(
    onDismiss: () -> Unit = {},
) {
    var selected by remember { mutableStateOf<PhoneVO?>(null) }

    Component(
        vmStatus = VmStatus.OK,
        phoneList = previewPhones,
        selectedPhone = selected,
        onDismiss = onDismiss,
        onPhoneClick = { selected = it },
        onConfirm = {},
    )
}

// ───────────── Component (순수 UI) ─────────────
@Composable
private fun Component(
    vmStatus: VmStatus,
    phoneList: List<PhoneVO>,
    selectedPhone: PhoneVO?,
    onDismiss: () -> Unit,
    onPhoneClick: (PhoneVO) -> Unit,
    onConfirm: () -> Unit,
) {
    CpDialog(
        title = "단말기 선택",
        onDismiss = onDismiss,
        loading = vmStatus == VmStatus.LOADING,
        footer = {
            CpOutlineButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                onClick = onDismiss,
            ) {
                CpText(text = "취소", fontWeight = FontWeight.Medium, fontSize = 0.7f)
            }
            Spacer(modifier = Modifier.weight(1f))
            if (selectedPhone == null) {
                Spacer(modifier = Modifier.weight(1f))
            } else {
                CpOutlineButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    color = AppTheme.colors.accent,
                    onClick = onConfirm,
                ) {
                    CpText(
                        text = "선택",
                        fontWeight = FontWeight.SemiBold,
                        color = AppTheme.colors.accent,
                        fontSize = 0.7f
                    )
                }
            }
        },
    ) {
        Body(
            vmStatus = vmStatus,
            phoneList = phoneList,
            selectedPhone = selectedPhone,
            onPhoneClick = onPhoneClick,
        )
    }
}

@Composable
private fun Body(
    vmStatus: VmStatus,
    phoneList: List<PhoneVO>,
    selectedPhone: PhoneVO?,
    onPhoneClick: (PhoneVO) -> Unit,
) {
    when (vmStatus) {
        VmStatus.ERROR -> CenterMessageBox {
            Text("단말기 목록을 불러오지 못했습니다.", style = AppTheme.typography.caption)
        }

        VmStatus.OK, VmStatus.LOADING -> {
            if (phoneList.isEmpty()) {
                CenterMessageBox {
                    Text("선택 가능한 단말기가 없습니다.", style = AppTheme.typography.caption)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(
                        items = phoneList,
                        key = { it.modelIndex },
                    ) { phone ->
                        PhoneItem(
                            phone = phone,
                            selected = phone.modelIndex == selectedPhone?.modelIndex,
                            onClick = { onPhoneClick(phone) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PhoneItem(
    modifier: Modifier = Modifier,
    phone: PhoneVO,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(14.dp)
    val accent = AppTheme.colors.accent

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(if (selected) accent.copy(alpha = 0.08f) else Color.Transparent)
            .border(
                width = if (selected) 1.5.dp else 1.dp,
                color = if (selected) accent else AppTheme.colors.line,
                shape = shape,
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = phone.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = phone.model,
                style = AppTheme.typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "출고가 ${formatPrice(phone.price)}원",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (selected) accent else Color.Unspecified,
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        CpRadioIcon(selected = selected)
    }
}

// ───────────── 공통 ─────────────
@Composable
private fun CenterMessageBox(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun rememberDialogViewModelStoreOwner(): ViewModelStoreOwner {
    val parent = checkNotNull(LocalViewModelStoreOwner.current) {
        "ViewModelStoreOwner가 없습니다."
    }
    val owner = remember {
        object : ViewModelStoreOwner, HasDefaultViewModelProviderFactory {
            override val viewModelStore = ViewModelStore()
            override val defaultViewModelProviderFactory: ViewModelProvider.Factory =
                (parent as HasDefaultViewModelProviderFactory).defaultViewModelProviderFactory
            override val defaultViewModelCreationExtras: CreationExtras =
                (parent as HasDefaultViewModelProviderFactory).defaultViewModelCreationExtras
        }
    }
    DisposableEffect(owner) {
        onDispose { owner.viewModelStore.clear() }
    }
    return owner
}

private fun formatPrice(price: Int): String =
    NumberFormat.getNumberInstance(Locale.KOREA).format(price)

// ───────────── Preview ─────────────
private val previewPhones = listOf(
    PhoneVO(modelIndex = 1, name = "Galaxy S25", model = "SM-S931N", price = 1_155_000),
    PhoneVO(modelIndex = 2, name = "Galaxy S25+", model = "SM-S936N", price = 1_353_000),
    PhoneVO(modelIndex = 3, name = "Galaxy S25 Ultra", model = "SM-S938N", price = 1_698_400),
    PhoneVO(modelIndex = 4, name = "Galaxy Z Flip6", model = "SM-F741N", price = 1_398_100),
    PhoneVO(modelIndex = 5, name = "Galaxy A56", model = "SM-A566N", price = 674_300),
)

@Preview(name = "Portrait", widthDp = 360, heightDp = 780, showBackground = true)
@Composable
private fun PortraitPreview() = ScMobileDialogRoute(tkIdx = 0, mkIdx = 0, onDismiss = {}, onSelect = {})

@Preview(name = "Landscape", widthDp = 780, heightDp = 360, showBackground = true)
@Composable
private fun LandscapePreview() = ScMobileDialogRoute(tkIdx = 0, mkIdx = 0, onDismiss = {}, onSelect = {})