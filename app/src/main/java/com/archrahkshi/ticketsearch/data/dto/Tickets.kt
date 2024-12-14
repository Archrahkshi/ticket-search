package com.archrahkshi.ticketsearch.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Tickets(
    val tickets: List<Ticket>
)
