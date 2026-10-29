package com.mc.toysimplemonkey.core.packages.domain.repository


import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO

interface SimpleMobileRepository {
    suspend fun getMakers(tkIdx : Int) : Result<List<MakerVO>>

    suspend fun getMobileList(tkIdx : Int, mkIdx: Int) : Result<List<PhoneVO>>

    suspend fun getMobileDetail(
        tkIdx: Int,
        mdIdx: Int) : Result<Boolean>


}