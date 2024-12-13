package com.archrahkshi.ticketsearch.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price
)
