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
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


    @HiltViewModel
    class RideRequestViewModel @Inject constructor(
    private val fetchRideEstimateUseCase: FetchRideEstimateUseCase
    ) : ViewModel() {

    private val _validateForms = MutableLiveData<ValidationResult>()
    val validateForms: LiveData<ValidationResult>
        get() = _validateForms

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
                    "Erro ao carregar estimativa", e.message ?: "erro desconhecido"
                )
                Log.e("API RideRequestViewModel", "Estado: Error - ${e.message}", e)
              }
            }
        }
    }


    fun validateFormsFields(fieldForms : RideEstimateRequest) : ValidationResult{
    val validateFields = ValidationResult()

    if (!fieldForms.customerId.nonEmpty())
        validateFields.customerId = true

    var addressOrigin = fieldForms.origin.validator()
        .nonEmpty()
        .contains(fieldForms.destination)
        .check()

    if (!fieldForms.origin.nonEmpty())
        validateFields.origin = true

    if (!fieldForms.destination.nonEmpty())
        validateFields.origin = true

    return validateFields
    }

