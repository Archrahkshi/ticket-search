package com.archrahkshi.ticketsearch.domain.repository

import com.archrahkshi.ticketsearch.domain.vo.FlattenedTicketOffer

interface TicketOffersRepository {
    suspend fun getTicketOffers(): List<FlattenedTicketOffer>
}
