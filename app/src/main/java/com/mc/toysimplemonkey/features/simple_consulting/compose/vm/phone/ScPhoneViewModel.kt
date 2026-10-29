package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.phone

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.mc.toysimplemonkey.core.common.VmStatus
import com.mc.toysimplemonkey.core.packages.domain.repository.SimpleMobileRepository
import com.mc.toysimplemonkey.core.packages.domain.use_case.ChangePhoneUseCase
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO


import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ScPhoneViewModel.Factory::class)
class ScPhoneViewModel @AssistedInject constructor(
    @Assisted("tkIdx") private val tkIdx: Int,
    @Assisted("mkIdx") private val mkIdx: Int,
    private val repository: SimpleMobileRepository,
    private val useCase: ChangePhoneUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("tkIdx") tkIdx: Int,
            @Assisted("mkIdx") mkIdx: Int,
        ): ScPhoneViewModel
    }

    private val _state = MutableStateFlow(ScPhoneState())
    val state: StateFlow<ScPhoneState> = _state.asStateFlow()

    init {
        loadMobileList()
    }

    fun onAction(action: ScPhoneAction) {
        when(action){
            is ScPhoneAction.SelectMobileAction -> selectMobileAction(
                selectedPhone = action.phone
            )
            is ScPhoneAction.ConfirmAction -> confirmAction(
                onResult = action.onResult
            )
        }
    }

    private fun updateState(
        vmStatus: VmStatus? = null,
        selectedPhone: PhoneVO? = null,
        phoneList: List<PhoneVO>? = null,
    ) {
        _state.update {
            it.copy(
                vmStatus = vmStatus ?: it.vmStatus,
                phoneList = phoneList ?: it.phoneList,
                selectedPhone = selectedPhone ?: it.selectedPhone,
            )
        }
    }

    private fun loadMobileList() {
        updateState(vmStatus = VmStatus.LOADING)
        viewModelScope.launch {
            repository.getMobileList(tkIdx= tkIdx, mkIdx = mkIdx)
                .onSuccess {
                    Log.d("VmHcPhoneViewModel", "조회 성공: tkIdx${tkIdx}, mkIdx${mkIdx}건")
                    updateState(
                        vmStatus = VmStatus.OK,
                        phoneList = it,
                    )
                }
                .onFailure { e ->
                    Log.e("VmHcPhoneViewModel", "제조사 휴대폰 목록 조회 실패: ${e.message}", e)
                    updateState(vmStatus = VmStatus.ERROR)
                }
        }

    }


    private fun confirmAction(onResult: (PhoneVO) -> Unit) {
        updateState(vmStatus = VmStatus.LOADING)
        val selected = state.value.selectedPhone
        if(selected != null){
            viewModelScope.launch {
                useCase(
                    tkIdx = tkIdx,
                    phone = selected
                ).onSuccess {
                    Log.d("VmHcPhoneViewModel", "상세 조회 성공: psIdx=${it.name}, ")
                    updateState(vmStatus = VmStatus.OK,)
                    onResult(it)


                    }
                    .onFailure { e ->
                        Log.e("VmHcPhoneViewModel", "휴대폰 상세 조회 실패: ${e.message}", e)
                        updateState(vmStatus = VmStatus.ERROR)
                    }
            }
        }
    }




    private fun selectMobileAction(selectedPhone: PhoneVO) {
        updateState(selectedPhone = selectedPhone)
    }



}