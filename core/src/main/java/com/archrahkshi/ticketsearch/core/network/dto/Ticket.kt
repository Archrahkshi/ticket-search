package com.archrahkshi.ticketsearch.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Ticket(
    val id: Int,
    val badge: String? = null,
    val price: Price,
    val providerName: String,
    val company: String,
    val departure: Location,
    val arrival: Location,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangable: Boolean
)
