package com.archrahkshi.ticketsearch.data

import kotlinx.serialization.Serializable

@Serializable
data class Offers(val offers: List<Offer>)

@Serializable
data class Offer(val id: Int, val title: String, val town: String, val price: Price)

@Serializable
data class Price(val value: Int)
