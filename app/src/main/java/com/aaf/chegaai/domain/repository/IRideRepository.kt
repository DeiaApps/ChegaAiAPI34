package com.aaf.chegaai.domain.repository
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import com.aaf.chegaai.domain.model.RideEstimate
import com.aaf.chegaai.domain.model.RideEstimateRequest
import com.aaf.chegaai.utils.RideResult

interface IRideRepository {

    suspend fun getRideEstimate(request: RideEstimateRequest): RideResult<RideEstimate>
}


//suspend fun getRideEstimate(customerId: String, origin: String, destination: String) : RideResult<RideEstimateRequest>

//suspend fun getRideDetails(customerId: String, driverId: Int) : RideResult<RideDetails>