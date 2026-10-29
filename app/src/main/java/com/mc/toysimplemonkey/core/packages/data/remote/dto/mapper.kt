package com.mc.toysimplemonkey.core.packages.data.remote.dto

import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO

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