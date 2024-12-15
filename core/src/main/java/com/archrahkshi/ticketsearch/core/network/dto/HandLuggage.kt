package com.archrahkshi.ticketsearch.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class HandLuggage(
    val hasHandLuggage: Boolean,
    val size: String? = null
)
