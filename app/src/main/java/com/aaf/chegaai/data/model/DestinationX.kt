package com.aaf.chegaai.data.model

data class DestinationX(
    val geocoderStatus: GeocoderStatus,
    val placeId: String,
    val type: List<String>
)