package com.mc.toysimplemonkey.core.packages.data.repository

//import com.mc.toysimplemonkey.packages.data.remote.api.PlanApi
//import com.mc.toysimplemonkey.features.home_consulting.data.model.PlanDto
//import com.mc.toysimplemonkey.features.home_consulting.data.model.toPlanDtoDetail
//import com.mc.toysimplemonkey.packages.domain.repository.SimplePlanRepository
//
//import javax.inject.Inject
//import kotlin.coroutines.cancellation.CancellationException
//
//
//class SimplePlanRepositoryImpl @Inject constructor(
//    private val planApi: PlanApi
//) : SimplePlanRepository {
//
//    override suspend fun getPlanDetail(pIdx : Int): Result<PlanDto> = safeCall {
//        planApi.getModeDetailData(
//            pIdx = pIdx
//        ).toPlanDtoDetail()
//    }
//
//
//
//    private suspend fun <T> safeCall(block: suspend () -> T): Result<T> =
//        try {
//            Result.success(block())
//        } catch (e: CancellationException) {
//            throw e
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//
//
//}