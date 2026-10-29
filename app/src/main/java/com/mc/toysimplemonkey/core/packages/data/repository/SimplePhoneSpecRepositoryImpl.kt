package com.mc.toysimplemonkey.core.packages.data.repository

import com.mc.toysimplemonkey.core.common.safeCall
import com.mc.toysimplemonkey.core.packages.data.remote.api.PhoneSpecApi

import com.mc.toysimplemonkey.core.packages.domain.repository.SimplePhoneSpecRepository

import javax.inject.Inject


class SimplePhoneSpecRepositoryImpl @Inject constructor(
    private val phoneSpecApi: PhoneSpecApi
) : SimplePhoneSpecRepository {

    override suspend fun getPhoneSpecDetail(psIdx : Int): Result<String> = safeCall {
        phoneSpecApi.getModeDetailData(
            psIdx = psIdx
        )
    }.map {
        it.phoneImages.firstOrNull()?.piPath.orEmpty()
    }







}