package com.archrahkshi.ticketsearch.domain.repository

import com.archrahkshi.ticketsearch.domain.vo.SimplifiedTicket

interface TicketRepository {
    suspend fun getTickets(): List<SimplifiedTicket>
}
