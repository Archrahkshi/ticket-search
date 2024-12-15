package com.archrahkshi.ticketsearch.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price
)
