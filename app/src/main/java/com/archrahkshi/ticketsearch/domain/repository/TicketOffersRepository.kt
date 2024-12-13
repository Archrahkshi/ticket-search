package com.archrahkshi.ticketsearch.domain.repository

import com.archrahkshi.ticketsearch.data.vo.FlattenedTicketOffer

interface TicketOffersRepository {
    suspend fun getTicketOffers(): List<FlattenedTicketOffer>
}
