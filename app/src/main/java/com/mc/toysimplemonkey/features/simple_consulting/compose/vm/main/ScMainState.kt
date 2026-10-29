package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main

import com.mc.toysimplemonkey.core.packages.domain.vo.simple.TelecomType
import com.mc.toysimplemonkey.core.common.VmStatus
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PlanVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.SubsidyType

data class ScMainState(
    val vmStatus: VmStatus = VmStatus.OK,

    val telecomType: TelecomType = TelecomType.SK,
    val subsidyType: SubsidyType = SubsidyType.GONGSI,
    val maker: MakerVO? = null,
    val makerList: List<MakerVO> = listOf(),
    val phone: PhoneVO? = null,
    val plan: PlanVO? = null,

    val showTelSheet: Boolean = false,
    val showMakerSheet: Boolean = false,
    val showPhoneDialog: Boolean = false,
    val showPlanDialog: Boolean = false,
)
