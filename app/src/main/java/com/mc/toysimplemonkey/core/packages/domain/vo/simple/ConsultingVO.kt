package com.mc.toysimplemonkey.core.packages.domain.vo.simple

data class ConsultingVO (
    val telecomType: TelecomType = TelecomType.SK,
    val subsidyType: SubsidyType = SubsidyType.GONGSI,
    val maker: MakerVO? = null,
    val makerList: List<MakerVO> = listOf(),
    val phoneVO: PhoneVO? = null,
    val planVO: PlanVO? = null,
)

