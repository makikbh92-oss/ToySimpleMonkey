package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.plan

import com.mc.toysimplemonkey.core.common.VmStatus
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO


data class ScPhoneState(
    val vmStatus: VmStatus = VmStatus.LOADING,
    val phoneList: List<PhoneVO> = emptyList(),
    val selectedPhone: PhoneVO? = null,
)

