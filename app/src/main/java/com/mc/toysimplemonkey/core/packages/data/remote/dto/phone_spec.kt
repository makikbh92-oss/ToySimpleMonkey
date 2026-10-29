package com.mc.toysimplemonkey.core.packages.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class PhoneDetailResponse(
    @SerialName("result") val result: String? = null, // Y/N
    @SerialName("PHONE_SPEC") val phoneSpec: PhoneSpecDto? = null,
    @SerialName("PHONE_IMG") val phoneImages: List<PhoneImgDto> = emptyList(),
    // 현재 빈 배열만 확인되어 구조를 모르므로 JsonElement로 받아둠 (구조 확인 후 교체)
    @SerialName("PHONE_OPTION_NUM") val phoneOptionNum: List<JsonElement> = emptyList(),
)

@Serializable
data class PhoneSpecDto(
    @SerialName("PS_idx") val psIdx: String? = null,
    @SerialName("mk_idx") val mkIdx: String? = null,
    @SerialName("mks_idx") val mksIdx: String? = null,
    @SerialName("PS_order") val order: String? = null,
    @SerialName("PS_pt_idx") val ptIdx: String? = null,
    @SerialName("PS_name") val name: String? = null,
    @SerialName("PS_psm_idx") val psmIdx: String? = null,
    @SerialName("PS_model") val model: String? = null,
    @SerialName("PS_os") val os: String? = null,
    @SerialName("PS_cpu") val cpu: String? = null,
    @SerialName("PS_display") val display: String? = null,
    @SerialName("PS_display_size") val displaySize: String? = null,
    @SerialName("PS_camera_front") val cameraFront: String? = null,
    @SerialName("PS_camera_back") val cameraBack: String? = null,
    @SerialName("PS_weight") val weight: String? = null,
    @SerialName("PS_inMemory") val inMemory: String? = null,
    @SerialName("PS_exMemory") val exMemory: String? = null,
    @SerialName("PS_ram") val ram: String? = null,
    @SerialName("PS_battery") val battery: String? = null,
    @SerialName("PS_info1") val info1: String? = null,
    @SerialName("PS_info2") val info2: String? = null,
    @SerialName("PS_mobileGrade") val mobileGrade: String? = null,
    @SerialName("PS_type_analytics") val typeAnalytics: String? = null,
    @SerialName("PS_release_date") val releaseDate: String? = null,
    @SerialName("PS_regi_date") val regiDate: String? = null,
    @SerialName("PS_edit_date") val editDate: String? = null,
    @SerialName("PS_keyword") val keyword: String? = null,
    @SerialName("PS_dual_number") val dualNumber: String? = null,
    @SerialName("PS_allImg_path") val allImgPath: String? = null,
    @SerialName("PS_isAllImg") val isAllImg: String? = null,
    @SerialName("PS_spec_nfc") val specNfc: String? = null,
    @SerialName("PS_spec_type") val specType: String? = null,
    @SerialName("PS_spec_film") val specFilm: String? = null,
    @SerialName("PS_spec_usim") val specUsim: String? = null,
    @SerialName("PS_spec_component") val specComponent: String? = null,
    @SerialName("PS_spec_design") val specDesign: String? = null,
    @SerialName("PS_spec_special") val specSpecial: String? = null,
    @SerialName("PS_spec_camera") val specCamera: String? = null,
    @SerialName("PS_spec_asinfo") val specAsInfo: String? = null,
    @SerialName("PS_spec_aod") val specAod: String? = null,
    @SerialName("PS_spec_pay") val specPay: String? = null,
    @SerialName("PS_spec_ip") val specIp: String? = null,
    @SerialName("PS_device_tag") val deviceTag: String? = null,
)

@Serializable
data class PhoneImgDto(
    @SerialName("PI_idx") val piIdx: String? = null,
    @SerialName("ps_idx") val psIdx: String? = null,   // 소문자 주의
    @SerialName("PI_path") val piPath: String? = null,
    @SerialName("PI_color_hax") val colorHex: String? = null, // "#000000" / "ff8874" 형식이 섞여 있음
    @SerialName("PI_color_name") val colorName: String? = null,
    @SerialName("PI_color_name_eng") val colorNameEng: String? = null,
    @SerialName("PI_edit_data") val editDate: String? = null, // 서버 키가 data로 오타 → 그대로 매핑
)