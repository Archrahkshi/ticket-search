package com.archrahkshi.ticketsearch.domain.vo

data class FlattenedTicketOffer(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Int
)
