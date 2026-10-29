package com.mc.toysimplemonkey.core.packages.domain.use_case


import com.mc.toysimplemonkey.core.packages.domain.repository.SimpleMobileRepository
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.ConsultingVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.TelecomType
import javax.inject.Inject

class ChangeTelecomUseCase @Inject constructor(
    private val repository: SimpleMobileRepository
) {
    suspend operator fun invoke(
        telecomType: TelecomType,
        maker: MakerVO? = null,
    ): Result<ConsultingVO> =
        repository.getMakers(telecomType.index).map {
            makers ->
            val result = maker
                ?.takeIf { current -> makers.any { it.index == current.index } }
                ?: makers.first()

            ConsultingVO(
                telecomType = telecomType,
                makerList = makers,
                maker = result,
            )
        }



}