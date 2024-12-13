package com.archrahkshi.ticketsearch.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffers(
    val ticketsOffers: List<TicketOffer>
)
