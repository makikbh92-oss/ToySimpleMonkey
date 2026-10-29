package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main

import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.TelecomType

sealed interface ScMainAction {

    data class SelectTelecomAction(
        val telecomType: TelecomType = TelecomType.SK,
        val maker: MakerVO? = null,
        val isInitial: Boolean = false
    ) : ScMainAction
    data class SelectMakerAction(
        val maker: MakerVO
    ): ScMainAction
    data class SelectPhoneAction(
        val phone: PhoneVO,
    ) : ScMainAction

    data object ClickTelecomAction : ScMainAction
    data object DismissTelecomSheetAction: ScMainAction
    data object ClickMakerAction : ScMainAction
    data object DismissMakerSheetAction: ScMainAction

    data object ClickPhoneAction : ScMainAction
    data object DismissPhoneDialogAction: ScMainAction

    data object ClickPlanAction : ScMainAction
    data object DismissPlanDialogAction: ScMainAction

    data object InputEtcPayAction : ScMainAction


}