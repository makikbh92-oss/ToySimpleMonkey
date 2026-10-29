package com.mc.toysimplemonkey.core.packages.data.remote.dto

import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MakerDto(
    @SerialName("MK_idx") val makerIdx: String? = null,
    @SerialName("MK_name") val makerName: String? = null,
    @SerialName("MK_order") val order: String? = null,
    @SerialName("MK_isHidden") val isHidden: String? = null, // Y/N
)

@Serializable
data class MakerListResponse(
    @SerialName("LIST") val list: List<MakerDto> = emptyList(),
)





@Serializable
data class MobileTrendListResponse(
    @SerialName("LIST") val list: List<MobileTrendItemDto> = emptyList(),
)

@Serializable
data class MobileTrendItemDto(
    @SerialName("eventYN") val eventYn: String? = null, // Y/N
    @SerialName("MD_idx") val mdIdx: String? = null,
    @SerialName("MD_model") val model: String? = null,
    @SerialName("MD_release_price") val releasePrice: String? = null, // 출고가
    @SerialName("MD_special_situation") val specialSituation: String? = null,
    @SerialName("PS_idx") val psIdx: String? = null,
    @SerialName("PS_name") val name: String? = null,
    @SerialName("PS_device_tag") val deviceTags: List<String> = emptyList(),
    @SerialName("PS_keyword") val keywords: List<String> = emptyList(),
    @SerialName("TD_open_subsidy") val openSubsidy: String? = null,
    @SerialName("TD_open_subsidy_sec") val openSubsidySec: String? = null,
    @SerialName("TD_open_subsidy_new") val openSubsidyNew: String? = null,
    @SerialName("P_plan_name") val planName: String? = null,
    @SerialName("P_plan_price_choice25") val planPriceChoice25: Int? = null,        // 숫자(Int)로 내려옴
    @SerialName("P_plan_price_choice25_result") val planPriceChoice25Result: Int? = null, // 숫자(Int)로 내려옴
)


@Serializable
data class MobileModelDetailListResponse(
    @SerialName("LIST") val list: List<MobileModelDetailDto> = emptyList(),
)

@Serializable
data class MobileModelDetailDto(
    @SerialName("PS_order") val psOrder: String? = null,
    @SerialName("MD_idx") val mdIdx: String? = null,
    @SerialName("MD_model") val model: String? = null,
    @SerialName("PT_type") val networkType: String? = null,            // 5G / LTE 등
    @SerialName("MD_price_type") val priceType: String? = null,        // Y/N
    @SerialName("MD_release_price") val releasePrice: String? = null,  // 출고가
    @SerialName("MD_isHalbuMonth48") val isHalbuMonth48: String? = null, // Y/N
    @SerialName("MD_reservat") val reservat: String? = null,           // "NONE" 등
    @SerialName("p_defaultPlan_idx") val defaultPlanIdx: String? = null, // 키가 소문자 p 시작 주의
    @SerialName("PS_idx") val psIdx: String? = null,
    @SerialName("PS_name") val name: String? = null,
    @SerialName("MK_idx") val mkIdx: String? = null,
    @SerialName("MK_name") val makerName: String? = null,
    @SerialName("CH_chulgo_date") val chulgoDate: String? = null,
    @SerialName("PS_release_date") val psReleaseDate: String? = null,
    @SerialName("MD_release_date") val mdReleaseDate: String? = null,
    @SerialName("IC_name") val icName: String? = null,                 // null로 오는 경우 있음
    @SerialName("PS_keyword") val keyword: String? = null,             // 여기선 배열이 아닌 콤마 구분 문자열
    @SerialName("PS_dual_number") val dualNumber: String? = null,
    @SerialName("MD_trans_support") val transSupport: String? = null,  // "NEW" 등
)
