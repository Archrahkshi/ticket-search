package com.archrahkshi.ticketsearch.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val town: String,
    val date: String,
    val airport: String
)
