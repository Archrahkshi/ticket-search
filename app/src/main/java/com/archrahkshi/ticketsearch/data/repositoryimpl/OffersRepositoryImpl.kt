package com.archrahkshi.ticketsearch.data.repositoryimpl

import com.archrahkshi.ticketsearch.core.network.ApiInterface
import com.archrahkshi.ticketsearch.data.vo.FlattenedOffer
import com.archrahkshi.ticketsearch.domain.repository.OffersRepository

class OffersRepositoryImpl(private val apiClient: ApiInterface) : OffersRepository {
    override suspend fun getOffers() = apiClient.getConcertOffers().offers.map {
        FlattenedOffer(it.id, it.title, it.town, it.price.value)
    }
}
