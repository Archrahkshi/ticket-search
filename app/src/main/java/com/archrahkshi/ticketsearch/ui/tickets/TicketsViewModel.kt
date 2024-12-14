package com.archrahkshi.ticketsearch.ui.tickets

import androidx.lifecycle.ViewModel
import com.archrahkshi.ticketsearch.domain.repository.TicketRepository

class TicketsViewModel(private val ticketRepository: TicketRepository) : ViewModel() {
    suspend fun getTickets() = ticketRepository.getTickets()
}
