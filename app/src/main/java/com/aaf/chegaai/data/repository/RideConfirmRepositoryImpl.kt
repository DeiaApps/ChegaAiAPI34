package com.aaf.chegaai.data.repository
/**
 * Created by Andréa Fonsêca on 12/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import com.aaf.chegaai.data.model.PATCH.RideConfirmRequestDTO
import com.aaf.chegaai.data.services.IRideAPI
import javax.inject.Inject


class RideConfirmRepositoryImpl  @Inject constructor(
    private val api: IRideAPI
) {
    suspend fun confirmRide(request: RideConfirmRequestDTO): RideConfirmRequestDTO {
        val response = api.confirmRide(request)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Response body is null")
        } else {
            throw Exception("Failed to confirm ride: ${response.errorBody()?.string()}")
        }
    }
}