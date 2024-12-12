package com.archrahkshi.ticketsearch.domain.usecase

import com.archrahkshi.ticketsearch.domain.repository.OffersRepository

class ConcertOffersUseCase(private val repository: OffersRepository) {
    suspend fun getOffers() = repository.getOffers()
}
