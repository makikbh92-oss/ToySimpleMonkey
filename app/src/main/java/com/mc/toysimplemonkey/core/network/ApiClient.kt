package com.mc.toysimplemonkey.core.network
import okhttp3.ResponseBody
import retrofit2.http.POST

import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiClient {

    @POST
    suspend fun post(
        @Url url: String,
        @QueryMap params: Map<String, String>
    ): ResponseBody
}