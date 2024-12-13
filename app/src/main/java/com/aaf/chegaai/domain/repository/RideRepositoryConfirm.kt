package com.aaf.chegaai.domain.repository

import com.aaf.chegaai.domain.model.RideConfirmation

/**
 * Created by Andréa Fonsêca on 12/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

interface RideRepositoryConfirm {
    suspend fun confirmRide(driverId: Int): RideConfirmation
}