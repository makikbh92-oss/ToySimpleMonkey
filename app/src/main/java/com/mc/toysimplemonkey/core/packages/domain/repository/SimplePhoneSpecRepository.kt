package com.mc.toysimplemonkey.core.packages.domain.repository


interface SimplePhoneSpecRepository {
    suspend fun getPhoneSpecDetail(psIdx : Int) : Result<String>

}