package com.aaf.chegaai.data.model.PATCH

data class RideConfirmRequestDTO(//RideConfirmResponse
    val customer_id: String,
    val destination: String,
    val distance: Int,
    val driver: DriverDTO,
    val duration: String,
    val origin: String,
    val value: Double
)

