package com.mc.toysimplemonkey.core.packages.data.remote.api

import com.mc.toysimplemonkey.core.network.ApiClient
import com.mc.toysimplemonkey.core.packages.data.remote.dto.MakerListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlanApi @Inject constructor(
    private val apiClient: ApiClient

) {

//    suspend fun getModeListYogumGroup(
//        tkIdx: Int = 1,
//        showAll: Boolean = false,
//    ): MakerListResponse = withContext(Dispatchers.IO) {
//        val raw = apiClient.post(
//            url = "_app/mobile/modeListMobileMaker",
//            params = mapOf(
//                "tk_idx" to tkIdx.toString(),
//                "showAllMaker" to if (showAll) "Y" else "N",
//            ),
//        ).string()
//        json.decodeFromString<MakerListResponse>(raw)
//    }







}