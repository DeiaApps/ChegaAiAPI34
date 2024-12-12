package com.aaf.chegaai.data.model

import com.aaf.chegaai.domain.model.RideEstimate

data class RideEstimateDTO(
    val destination: Destination,
    val distance: Int,
    val duration: Int,
    val options: List<Option>,
    val origin: Origin,
    val routeResponse: RouteResponse
)

