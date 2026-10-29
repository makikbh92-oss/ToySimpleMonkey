package com.mc.toysimplemonkey.core.packages.data.repository

import com.mc.toysimplemonkey.core.common.safeCall
import com.mc.toysimplemonkey.core.packages.data.remote.api.MobileApi
import com.mc.toysimplemonkey.core.packages.data.remote.dto.toVOList
import com.mc.toysimplemonkey.core.packages.domain.repository.SimpleMobileRepository
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.MakerVO
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.PhoneVO

import javax.inject.Inject


class SimpleMobileRepositoryImpl @Inject constructor(
    private val mobileApi: MobileApi
) : SimpleMobileRepository {

    override suspend fun getMakers(tkIdx : Int): Result<List<MakerVO>> = safeCall {
        mobileApi.getModeListMobileMaker(
            tkIdx = tkIdx
        )
    }.map {
        it.toVOList()
    }

    override suspend fun getMobileList(tkIdx: Int, mkIdx: Int): Result<List<PhoneVO>>
    = safeCall {
        mobileApi.getModeListMobileTrend(
            tkIdx = tkIdx,
            mkIdx = mkIdx
        )
    }.map { it.toVOList() }

    override suspend fun getMobileDetail(tkIdx: Int, mdIdx: Int): Result<Boolean>
            = safeCall {
        mobileApi.getModeDetailData(
            tkIdx = tkIdx,
            mdIdx = mdIdx
        )
    }.map {
        it.list.firstOrNull()?.isHalbuMonth48 == "Y"
    }









}