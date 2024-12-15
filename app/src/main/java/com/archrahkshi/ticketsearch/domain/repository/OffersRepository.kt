package com.archrahkshi.ticketsearch.domain.repository

import com.archrahkshi.ticketsearch.domain.vo.FlattenedOffer

interface OffersRepository {
    suspend fun getOffers(): List<FlattenedOffer>
}
