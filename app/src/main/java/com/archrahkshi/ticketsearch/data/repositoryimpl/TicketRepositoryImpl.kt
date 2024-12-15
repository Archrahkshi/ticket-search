package com.archrahkshi.ticketsearch.data.repositoryimpl

import com.archrahkshi.ticketsearch.core.network.ApiInterface
import com.archrahkshi.ticketsearch.data.getTimeDifference
import com.archrahkshi.ticketsearch.data.getTimeFromDate
import com.archrahkshi.ticketsearch.domain.repository.TicketRepository
import com.archrahkshi.ticketsearch.domain.vo.SimplifiedTicket

class TicketRepositoryImpl(private val apiClient: ApiInterface) : TicketRepository {
    override suspend fun getTickets() = apiClient.getTickets().tickets.map {
        SimplifiedTicket(
            it.badge,
            it.price.value,
            getTimeFromDate(it.departure.date),
            it.departure.airport,
            getTimeFromDate(it.arrival.date),
            it.arrival.airport,
            getTimeDifference(it.departure.date, it.arrival.date),
            it.hasTransfer
        )
    }
}
