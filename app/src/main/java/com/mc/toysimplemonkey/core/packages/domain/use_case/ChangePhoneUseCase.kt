package com.mc.toysimplemonkey.core.packages.domain.use_case

import com.mc.toysimplemonkey.core.packages.domain.repository.SimpleMobileRepository
import com.mc.toysimplemonkey.core.packages.domain.repository.SimplePhoneSpecRepository
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.ConsultingVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.TelecomType
import javax.inject.Inject

class ChangePhoneUseCase @Inject constructor(
    private val mobile: SimpleMobileRepository,
    private val phoneSpec: SimplePhoneSpecRepository,
) {
    suspend operator fun invoke(
        phone: PhoneVO,
        tkIdx: Int,
    ): Result<PhoneVO> {
        val isHalbu = mobile.getMobileDetail(
            tkIdx = tkIdx,
            mdIdx = phone.modelIndex,
        ).getOrElse { return Result.failure(it) }

        val imageUrl = phoneSpec.getPhoneSpecDetail(phone.index)
            .getOrElse { return Result.failure(it) }

        return Result.success(
            phone.copy(
                isHalbuMonth48 = isHalbu,
                imageUrl = imageUrl,
            )
        )
    }


}