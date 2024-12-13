package com.archrahkshi.ticketsearch.data.repositoryimpl

import com.archrahkshi.ticketsearch.core.apiClient
import com.archrahkshi.ticketsearch.data.vo.FlattenedTicketOffer
import com.archrahkshi.ticketsearch.domain.repository.TicketOffersRepository

class TicketOffersRepositoryImpl : TicketOffersRepository {
    override suspend fun getTicketOffers() =
        apiClient.getTicketOffers().ticketsOffers.map {
            FlattenedTicketOffer(it.id, it.title, it.timeRange, it.price.value)
        }
}
