package com.archrahkshi.ticketsearch.data.repositoryimpl

import com.archrahkshi.ticketsearch.core.apiClient
import com.archrahkshi.ticketsearch.data.vo.FlattenedOffer
import com.archrahkshi.ticketsearch.domain.repository.OffersRepository

class OffersRepositoryImpl : OffersRepository {
    override suspend fun getOffers() = apiClient.getOffers().offers.map {
        FlattenedOffer(it.id, it.title, it.town, it.price.value)
    }
}
