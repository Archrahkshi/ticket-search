package com.archrahkshi.ticketsearch.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Tickets(
    val tickets: List<Ticket>
)
