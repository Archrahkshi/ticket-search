package com.archrahkshi.ticketsearch.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Luggage(
    val hasLuggage: Boolean,
    val price: Price? = null
)
