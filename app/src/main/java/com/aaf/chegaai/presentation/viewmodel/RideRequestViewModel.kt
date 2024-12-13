package com.aaf.chegaai.presentation.viewmodel
/**
* Created by Andréa Fonsêca on 08/12/2024
* Copyright (c) 2024 Andréa A. Fonsêca
* Licensed under the MIT License.
* See LICENSE file for details.
**/

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaf.chegaai.domain.model.RideEstimate
import com.aaf.chegaai.domain.model.RideEstimateRequest
import com.aaf.chegaai.domain.usecase.FetchRideEstimateUseCase
import com.aaf.chegaai.domain.usecase.ValidationResult
import com.aaf.chegaai.utils.RideResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RideRequestViewModel @Inject constructor(
    private val fetchRideEstimateUseCase: FetchRideEstimateUseCase
) : ViewModel() {

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean> = _isFormValid

    private val _validationResult = MutableLiveData<ValidationResult>()
    val validationResult: LiveData<ValidationResult> = _validationResult

    private val _rideEstimate = MutableLiveData<RideResult<RideEstimate>>()
    val rideEstimate: LiveData<RideResult<RideEstimate>> = _rideEstimate

    fun fetchRideEstimate(customerId: String, origin: String, destination: String) {
        _rideEstimate.value = RideResult.Loading
            viewModelScope.launch {
                try {
                    val request = RideEstimateRequest(
                    customerId = customerId,
                    origin = origin,
                    destination = destination
                    )
                _rideEstimate.value = fetchRideEstimateUseCase(request)

            } catch (e: Exception) {
                _rideEstimate.value = RideResult.Error(
                    "Erro ao carregar estimativa", e.message ?: "Erro desconhecido"
                )
                Log.e("API RideRequestViewModel", "Estado: Error - ${e.message}", e)
              }
            }
        }

        fun validateAndProceed(customerId: String, origin: String, destination: String) {
            val validation = ValidationResult(
                customerId = customerId.isNotBlank(),
                origin = origin.isNotBlank(),
                destination = destination.isNotBlank()
            )
            _validationResult.value = validation
            _isFormValid.value = validation.completedForm
        }

        fun fetchRideEstimateIfValid(customerId: String, origin: String, destination: String) {
            if (_validationResult.value?.completedForm == true) {
                fetchRideEstimate(customerId, origin, destination)
            }
        }
    }
