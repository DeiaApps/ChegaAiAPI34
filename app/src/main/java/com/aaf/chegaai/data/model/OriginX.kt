package com.aaf.chegaai.data.model

data class OriginX(
    val geocoderStatus: GeocoderStatus,
    val placeId: String,
    val type: List<String>
)