package com.aaf.chegaai.data.services
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import com.aaf.chegaai.data.model.PATCH.RideConfirmRequestDTO
import com.aaf.chegaai.data.model.RideEstimateDTO
import com.aaf.chegaai.data.model.RideEstimateRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IRideAPI {

    @POST("ride/estimate")
    suspend fun getRideEstimate(
        @Body request: RideEstimateRequestDTO
    ): Response<RideEstimateDTO>

    @PATCH("ride/confirm")
    suspend fun confirmRide(
        @Body request: RideConfirmRequestDTO//RideConfirmRequestDTO
    ): Response<RideConfirmRequestDTO>

    /*    @GET("ride/{customer_id}")
    suspend fun getRideDetails(
        @Path("customer_id") customer_id: String,
        @Query("driver_id") driver_id: Int
    ) : Response<RideDetailsDTO>
}*/
}
