package com.mc.toysimplemonkey.core.packages.data.remote.api

import com.mc.toysimplemonkey.core.network.ApiClient
import com.mc.toysimplemonkey.core.packages.data.remote.dto.MakerListResponse
import com.mc.toysimplemonkey.core.packages.data.remote.dto.MobileModelDetailListResponse
import com.mc.toysimplemonkey.core.packages.data.remote.dto.MobileTrendListResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlinx.serialization.json.Json

class MobileApi @Inject constructor(
    private val apiClient: ApiClient,
    private val json: Json,
) {

    suspend fun getModeListMobileMaker(
        tkIdx: Int = 1,
        showAll: Boolean = false,
    ): MakerListResponse = withContext(Dispatchers.IO) {
        val raw = apiClient.post(
            url = "_app/mobile/modeListMobileMaker",
            params = mapOf(
                "tk_idx" to tkIdx.toString(),
                "showAllMaker" to if (showAll) "Y" else "N",
            ),
        ).string()
        json.decodeFromString<MakerListResponse>(raw)
    }


    suspend fun getModeListMobileTrend(
        tkIdx: Int,
        mkIdx: Int
    ): MobileTrendListResponse = withContext(Dispatchers.IO) {
        val raw = apiClient.post(
            url = "_app/mobile/modeListMobile_trend",
            params = mapOf(
                "tk_idx" to tkIdx.toString(),
                "mk_idx" to mkIdx.toString(),

            )
        ).string()
        json.decodeFromString<MobileTrendListResponse>(raw)
    }


    suspend fun getModeDetailData(
        tkIdx: Int,
        mdIdx: Int
    ): MobileModelDetailListResponse = withContext(Dispatchers.IO) {
        val raw = apiClient.post(
            url = "_app/mobile/modeDetailData",
            params = mapOf(
                "tk_idx" to tkIdx.toString(),
                "md_idx" to mdIdx.toString(),
            )
        ).string()
        json.decodeFromString<MobileModelDetailListResponse>(raw)
    }





}