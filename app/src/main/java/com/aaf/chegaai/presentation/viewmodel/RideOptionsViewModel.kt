package com.aaf.chegaai.presentation.viewmodel
/**
 * Created by Andréa Fonsêca on 12/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaf.chegaai.data.model.PATCH.RideConfirmRequestDTO
import com.aaf.chegaai.domain.usecase.ConfirmRideUseCase
import com.aaf.chegaai.utils.RideResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RideOptionsViewModel @Inject constructor(
    private val confirmRideUseCase: ConfirmRideUseCase
) : ViewModel() {

    private val _confirmationResult = MutableLiveData<RideResult<RideConfirmRequestDTO>>()
    val confirmationResult: LiveData<RideResult<RideConfirmRequestDTO>> = _confirmationResult

    fun confirmRide(request: RideConfirmRequestDTO) {
        viewModelScope.launch {
            _confirmationResult.value = RideResult.Loading
            try {
                val response = confirmRideUseCase.execute(request)
                _confirmationResult.value = RideResult.Success(response)
            } catch (e: Exception) {
                _confirmationResult.value = RideResult.Error("Erro ao tentar confirmar viagem",e.localizedMessage ?: "Unknown error")
            }
        }
    }
}

