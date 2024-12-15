package com.archrahkshi.ticketsearch.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffers(
    val ticketsOffers: List<TicketOffer>
)
