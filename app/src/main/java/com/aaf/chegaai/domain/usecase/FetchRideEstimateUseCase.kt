package com.aaf.chegaai.domain.usecase
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import com.aaf.chegaai.domain.model.RideEstimate
import com.aaf.chegaai.domain.model.RideEstimateRequest
import com.aaf.chegaai.domain.repository.IRideRepository
import com.aaf.chegaai.utils.RideResult
import javax.inject.Inject



class FetchRideEstimateUseCase @Inject constructor(
    private val repository: IRideRepository
){

    suspend operator fun invoke(request: RideEstimateRequest): RideResult<RideEstimate> {
        return repository.getRideEstimate(request)
    }

}

