package com.archrahkshi.ticketsearch.domain

import com.archrahkshi.ticketsearch.ui.apiClient

data class FlattenedOffer(val id: Int, val title: String, val town: String, val price: Int)

suspend fun getOffers(): List<FlattenedOffer> = apiClient.getOffers().offers.map {
    FlattenedOffer(it.id, it.title, it.town, it.price.value)
}
