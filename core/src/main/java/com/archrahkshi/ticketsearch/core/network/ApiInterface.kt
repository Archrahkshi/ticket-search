package com.archrahkshi.ticketsearch.core.network

import com.archrahkshi.ticketsearch.core.network.dto.Offers
import com.archrahkshi.ticketsearch.core.network.dto.Tickets
import com.archrahkshi.ticketsearch.core.network.dto.TicketsOffers
import retrofit2.http.GET

interface ApiInterface {
    @GET("offers.json")
    suspend fun getConcertOffers(): Offers

    @GET("offers_tickets.json")
    suspend fun getTicketOffers(): TicketsOffers

    @GET("tickets.json")
    suspend fun getTickets(): Tickets
}
