package com.archrahkshi.ticketsearch.data.repositoryimpl

import com.archrahkshi.ticketsearch.core.network.ApiInterface
import com.archrahkshi.ticketsearch.domain.repository.TicketOffersRepository
import com.archrahkshi.ticketsearch.domain.vo.FlattenedTicketOffer

class TicketOffersRepositoryImpl(private val apiClient: ApiInterface) : TicketOffersRepository {
    override suspend fun getTicketOffers() =
        apiClient.getTicketOffers().ticketsOffers.map {
            FlattenedTicketOffer(it.id, it.title, it.timeRange, it.price.value)
        }
}
