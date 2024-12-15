package com.archrahkshi.ticketsearch.domain.vo

data class SimplifiedTicket(
    val badge: String?,
    val price: Int,
    val departureTime: String,
    val departureAirport: String,
    val arrivalTime: String,
    val arrivalAirport: String,
    val duration: String,
    val hasTransfer: Boolean
)
