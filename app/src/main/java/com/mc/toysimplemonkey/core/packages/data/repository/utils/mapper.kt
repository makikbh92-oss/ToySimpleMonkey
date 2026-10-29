package com.mc.toysimplemonkey.core.packages.data.remote.dto

import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PlanVO

fun PlanDto.toVO(): PlanVO = PlanVO(
    index = pIdx.toIntOrZero(),
    name = pPlanName.orEmpty(),
    price = pPlanPrice ?: 0,
    lte = pLte.orEmpty(),
    tel = pTel.orEmpty(),
    sms = pSms.orEmpty(),
    gongsi = pBasicPrice ?: 0,
    // choiceBasic, choice12Month, choice24Month, choice36Month는 목록 응답에 없어 기본값(0) 사용
)

fun List<PlanDto>.toVOList(): List<PlanVO> = map { it.toVO() }

fun PlanListResponse.toVOList(): List<PlanVO> = list.toVOList()



fun PlanDetailDto.toVO(): PlanVO = PlanVO(
    index = pIdx.toIntOrZero(),
    name = pPlanName.orEmpty(),
    price = pPlanPrice.toIntOrZero(),
    lte = pLte.orEmpty(),
    tel = pTel.orEmpty(),
    sms = pSms.orEmpty(),
    gongsi = pPlanMappingPrice.toIntOrZero(),
    choiceBasic = pPlanPriceChoice25.toIntOrZero(),
    choice12Month = pPlanPrice12Month.toIntOrZero(),
    choice24Month = pPlanPrice24Month.toIntOrZero(),
    choice36Month = pPlanPrice36Month.toIntOrZero(),
)

fun List<PlanDetailDto>.toVOList(): List<PlanVO> = map { it.toVO() }

fun PlanDetailListResponse.toVOList(): List<PlanVO> = list.toVOList()

private fun String?.toIntOrZero(): Int = this?.trim()?.toIntOrNull() ?: 0


fun MakerDto.toVO(): MakerVO? {
    val index = makerIdx?.toIntOrNull() ?: return null
    val name = makerName?.takeIf { it.isNotBlank() } ?: return null
    return MakerVO(index = index, name = name)
}

fun MakerListResponse.toVOList(): List<MakerVO> =
    list.filter { it.isHidden != "Y" }   // 숨김은 여기서 제외
        .sortedByDescending { it.order?.toIntOrNull() ?: 0 }
        .mapNotNull { it.toVO() }




fun MobileTrendItemDto.toVO(): PhoneVO? {
    val index = psIdx?.trim()?.toIntOrNull() ?: return null
    val modelIndex = mdIdx?.trim()?.toIntOrNull() ?: return null
    val name = name?.trim()?.takeIf { it.isNotEmpty() } ?: return null

    return PhoneVO(
        index = index,
        name = name,
        price = releasePrice?.trim()?.toIntOrNull() ?: 0,
        model = model?.trim().orEmpty(),
        modelIndex = modelIndex,
        imageUrl = "",              // 목록 응답에 이미지 없음 → PHONE_IMG로 별도 채움
        isHalbuMonth48 = false,     // 목록 응답에 정보 없음 → modeDetailData(MD_isHalbuMonth48)로 채움
    )
}

fun MobileTrendListResponse.toVOList(): List<PhoneVO> =
    list.mapNotNull { it.toVO() }