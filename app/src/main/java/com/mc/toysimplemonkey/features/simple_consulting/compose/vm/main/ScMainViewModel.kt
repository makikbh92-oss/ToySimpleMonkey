package com.mc.toysimplemonkey.features.simple_consulting.compose.vm.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.TelecomType
import com.mc.toysimplemonkey.core.common.VmStatus
import com.mc.toysimplemonkey.core.packages.domain.use_case.ChangeTelecomUseCase
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PlanVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.SubsidyType

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScMainViewModel @Inject constructor(
    private  val changeTelecomUseCase: ChangeTelecomUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(value = ScMainState())
    val state: StateFlow<ScMainState> = _state.asStateFlow()

//    private val _event = MutableSharedFlow<VmHcMainEvent>()
//    val event: SharedFlow<VmHcMainEvent> =  _event.asSharedFlow()

    init{
        selectTelecomAction(isInitial = true)
    }

    fun onAction(action: ScMainAction) {
        when (action) {
            ScMainAction.ClickTelecomAction -> _state.update { it.copy(showTelSheet = true) }
            ScMainAction.DismissTelecomSheetAction -> _state.update { it.copy(showTelSheet = false) }
            ScMainAction.ClickMakerAction -> _state.update { it.copy(showMakerSheet = true) }
            ScMainAction.DismissMakerSheetAction -> _state.update { it.copy(showMakerSheet = false) }
            ScMainAction.ClickPhoneAction -> _state.update { it.copy(showPhoneDialog = true) }
            ScMainAction.DismissPhoneDialogAction -> _state.update { it.copy(showPhoneDialog = false) }
            ScMainAction.ClickPlanAction -> _state.update { it.copy(showPlanDialog = true) }
            ScMainAction.DismissPlanDialogAction -> _state.update { it.copy(showPlanDialog = false) }
            is ScMainAction.SelectTelecomAction -> selectTelecomAction(
                telecomType = action.telecomType,
                maker = action.maker,
                isInitial = action.isInitial
            )
            is ScMainAction.SelectMakerAction -> selectMakerAction(maker = action.maker)
            is ScMainAction.SelectPhoneAction -> selectPhoneAction(phone = action.phone)
            ScMainAction.InputEtcPayAction -> inputEtcPayAction()

        }
    }

//    fun onEvent(event: VmHcMainEvent){
//        when (event){
//            is VmHcMainEvent.ShowSnackbar -> {}
//        }
//    }

    private fun updateState(
        vmStatus: VmStatus? = null,
        telecomType: TelecomType? = null,
        subsidyType: SubsidyType? = null,
        maker: MakerVO? = null,
        makerList: List<MakerVO>? = null,
        phone: PhoneVO? = null,
        plan: PlanVO? = null,
        showTelSheet: Boolean? = null,
        showMakerSheet: Boolean? = null,
        showPhoneDialog: Boolean? = null,
        showPlanDialog: Boolean? = null,
    ) {
        _state.update { current ->
            current.copy(
                vmStatus = vmStatus ?: current.vmStatus,
                telecomType = telecomType ?: current.telecomType,
                subsidyType = subsidyType ?: current.subsidyType,
                maker = maker ?: current.maker,
                makerList = makerList ?: current.makerList,
                phone = phone ?: current.phone,
                plan = plan ?: current.plan,
                showTelSheet = showTelSheet ?: current.showTelSheet,
                showMakerSheet = showMakerSheet ?: current.showMakerSheet,
                showPhoneDialog = showPhoneDialog ?: current.showPhoneDialog,
                showPlanDialog = showPlanDialog ?: current.showPlanDialog,
            )
        }
    }



   private fun selectTelecomAction(
        telecomType: TelecomType = TelecomType.SK,
        maker: MakerVO? = null,
        isInitial: Boolean = false
    ) {
       if(state.value.telecomType == telecomType && !isInitial){
           updateState(vmStatus = VmStatus.OK,showTelSheet = false,)
           return
       }
        viewModelScope.launch {
            updateState(vmStatus = VmStatus.LOADING) // TODO: 실제 VmStatus 값으로 변경
            changeTelecomUseCase(
                telecomType = telecomType,
                maker = maker,
            )
                .onSuccess { consulting ->
                    _state.update {
                        it.copy(
                            vmStatus = VmStatus.OK,
                            telecomType = consulting.telecomType,
                            makerList = consulting.makerList,
                            maker = consulting.maker,
                            plan = consulting.planVO,
                            phone = consulting.phoneVO,
                            showTelSheet = false,
                        )
                    }
                }
                .onFailure {
                    updateState(vmStatus = VmStatus.ERROR) // TODO: 실제 VmStatus 값으로 변경
                }
        }
    }


    private fun selectMakerAction(maker: MakerVO){
        _state.update {
            it.copy(
                maker = maker,
                showMakerSheet = false,
                phone = null,
                plan = null,
            )
        }
    }

    private fun selectPhoneAction(phone: PhoneVO ){
       updateState(
           phone = phone,
           showPhoneDialog = false
       )
    }




    private fun inputEtcPayAction() {}

}