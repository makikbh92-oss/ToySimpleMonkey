package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.plan

import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO


sealed interface ScPhoneAction {
    data class SelectMobileAction(val phone: PhoneVO) : ScPhoneAction
    data class ConfirmAction(

        val onResult: (PhoneVO) -> Unit) : ScPhoneAction
}