package com.archrahkshi.ticketsearch.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val town: String,
    val date: String,
    val airport: String
)
