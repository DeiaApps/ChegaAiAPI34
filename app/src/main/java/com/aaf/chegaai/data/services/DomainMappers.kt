package com.aaf.chegaai.data.services
/**
 * Created by Andréa Fonsêca on 10/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import com.aaf.chegaai.data.model.Option
import com.aaf.chegaai.data.model.PATCH.DriverDTO
import com.aaf.chegaai.data.model.PATCH.RideConfirmRequestDTO
import com.aaf.chegaai.data.model.RideEstimateDTO
import com.aaf.chegaai.data.model.RideEstimateRequestDTO
import com.aaf.chegaai.domain.model.Driver
import com.aaf.chegaai.domain.model.RideConfirmation
import com.aaf.chegaai.domain.model.RideEstimate
import com.aaf.chegaai.domain.model.RideEstimateRequest
import com.aaf.chegaai.domain.model.RideOption


fun RideEstimateRequestDTO.toDomain(): RideEstimateRequest{
    return RideEstimateRequest(
        customerId = this.customer_id,
        origin = this.origin,
        destination = this.destination,
    )
}

fun DriverDTO.toDomain(): Driver{
    return Driver(
        id = this.id,
        name = this.name
    )
}

fun RideEstimateDTO.toDomain(): RideEstimate {
    return RideEstimate(
        destination = "${this.destination.latitude}, ${this.destination.longitude}",
        distance = this.distance,
        duration = this.duration,
        options = this.options.map { it.toDomain() },
        origin = "${this.destination.latitude}, ${this.destination.longitude}",
        route = this.routeResponse.toString()
    )
}

fun Option.toDomain(): RideOption{
    return RideOption(
        driverDescription = this.description,
        driverId = this.id,
        driverName = this.name,
        driverNote = this.review.rating,
        driverReviews = this.review.comment,
        tripValue = this.value,
        vehicle = this.vehicle
    )
}