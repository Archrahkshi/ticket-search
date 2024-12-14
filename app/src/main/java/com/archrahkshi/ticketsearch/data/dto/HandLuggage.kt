package com.archrahkshi.ticketsearch.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class HandLuggage(
    val hasHandLuggage: Boolean,
    val size: String? = null
)
