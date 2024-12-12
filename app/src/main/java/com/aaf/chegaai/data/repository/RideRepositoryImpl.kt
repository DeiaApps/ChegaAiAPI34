package com.aaf.chegaai.data.repository
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import com.aaf.chegaai.data.model.RideEstimateRequestDTO
import com.aaf.chegaai.data.services.IRideAPI
import com.aaf.chegaai.data.services.toDomain
import com.aaf.chegaai.domain.model.RideEstimate
import com.aaf.chegaai.domain.model.RideEstimateRequest
import com.aaf.chegaai.domain.repository.IRideRepository
import com.aaf.chegaai.utils.RideResult
import javax.inject.Inject


class RideRepositoryImpl @Inject constructor(
    private val apiService: IRideAPI
) : IRideRepository {

    override suspend fun getRideEstimate(request: RideEstimateRequest): RideResult<RideEstimate> {
        return try {
            val requestDTO = RideEstimateRequestDTO(
                customer_id = request.customerId,
                origin = request.origin,
                destination = request.destination
            )
            val response = apiService.getRideEstimate(requestDTO)

            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.let { responseDTO ->
                    RideResult.Success(responseDTO.toDomain())
                } ?: RideResult.Error("API RideRepositoryImpl - EMPTY_RESPONSE", "A resposta da API está vazia.")
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Ero desconhecido"
                RideResult.Error("NULL_BODY", "A resposta da API está vazia."

                )
            }
        } catch (e: Exception) {
            // Tratamento de erros inesperados
            RideResult.Error("RepositoryImpl UNKNOWN_ERROR", e.message ?: "Erro desconhecido.")
        }
    }
}
