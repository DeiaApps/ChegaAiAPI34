package com.aaf.chegaai.domain.usecase

import com.aaf.chegaai.data.model.PATCH.RideConfirmRequestDTO
import com.aaf.chegaai.data.repository.RideConfirmRepositoryImpl
import javax.inject.Inject

/**
 * Created by Andréa Fonsêca on 12/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

class ConfirmRideUseCase @Inject constructor(
    private val rideRepository: RideConfirmRepositoryImpl
) {
    suspend fun execute(request: RideConfirmRequestDTO): RideConfirmRequestDTO {
        return rideRepository.confirmRide(request)
    }
}