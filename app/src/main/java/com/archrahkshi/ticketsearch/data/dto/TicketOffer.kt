package com.archrahkshi.ticketsearch.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TicketOffer(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Price
)
