package com.archrahkshi.ticketsearch.ui.start

import androidx.lifecycle.ViewModel
import com.archrahkshi.ticketsearch.domain.usecase.ConcertOffersUseCase

class StartViewModel(private val concertOffersUseCase: ConcertOffersUseCase) : ViewModel() {
    suspend fun getOffers() = concertOffersUseCase.getOffers()
}
