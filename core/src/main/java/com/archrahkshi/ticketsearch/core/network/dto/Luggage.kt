package com.archrahkshi.ticketsearch.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Luggage(
    val hasLuggage: Boolean,
    val price: Price? = null
)
